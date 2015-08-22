package com.ld33.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public final class DebugLocalMultiplayer {

	private static final long CLIENT_LIFE = 2000;
	private static final long ROOM_LIFE = 2000;
	
	public static interface Listener {
		public void onRoomReady(final Room room);
		public void onDataReceived(final Client source, 
								   final byte[] data);
		public void onClientDisconnected(final Client client);
		public void onDisconnected();
	}
	
	public final class Client {
		private final Room room;
		private final String clientId;
		private final FileHandle clientHandle;
		private final FileHandle clientLifeHandle;
		private final FileHandle clientInboxHandle;
		private int messageCounter;

		public Client(final Room room,
					  final String clientId) {
			
			this.room = room;
			this.clientId = clientId;
			
			clientHandle = room.roomClientsHandle.child(clientId);
			clientHandle.mkdirs();
			
			clientLifeHandle = clientHandle.child("life.lf");
			clientInboxHandle = clientHandle.child("inbox");
			clientInboxHandle.mkdirs();
		}
		
		private void tick() {
			forceWriteString(clientLifeHandle, "alive", false);
		}
		
		private void readInbox() {
			final FileHandle[] inbox = clientInboxHandle.list();
			for(final FileHandle message : inbox) {
				final String sourceData = message.nameWithoutExtension();
				final String clientId = sourceData.substring(0, sourceData.indexOf('@'));
				
				final Client client = room.findClient(clientId);
				if(client != null) {
					listener.onDataReceived(client, forceReadBytes(message));
				}
				
				message.copyTo(clientHandle);
				message.delete();
			}
		}
		
		private void sendMessage(final Client source,
								 final byte[] bytes) {
			
			forceWriteBytes(clientInboxHandle.child(source.getClientId() + "@" + messageCounter + ".msg"), bytes, false);
			messageCounter += 1;
		}
		
		public String getClientId() {
			return clientId;
		}
	}
	
	public final class Room {
		private final Array<Client> tmpClientsArray = new Array<Client>();
		
		private final String roomId;
		private final FileHandle roomHandle;
		private final FileHandle roomLifeHandle;
		private final FileHandle roomClientsHandle;
		
		private final Array<Client> clients = new Array<Client>();
		private final Client localClient;
		
		private boolean connected = true;
		private boolean ready;
		
		public Room(final String roomId) {
			this.roomId = roomId;
			
			roomHandle = rootHandle.child(roomId);
			roomHandle.mkdirs();

			roomLifeHandle = roomHandle.child("life.lf");
			roomClientsHandle = roomHandle.child("clients");
			roomClientsHandle.mkdirs();
			
			final int localNumber = roomClientsHandle.list().length + 1;
			localClient = new Client(this, "Client" + localNumber);
			
			clients.add(localClient);
		}

		private void handleTick() {
			if(connected) {
				tick();
				localClient.tick();
			}
		}
		
		private void update() {
			if(connected) {
				if(ready) {
					handleDisconnections();
					localClient.readInbox();
				}
				else {
					handleConnections();
				}
			}
		}
		
		private void tick() {
			forceWriteString(roomLifeHandle, "alive", false);
		}
		
		private void handleDisconnections() {
			for(int i = 0; i < clients.size; i += 1) {
				final Client client = clients.get(i);
				final long clientLifeTick = client.clientLifeHandle.lastModified();
				
				if(clientLifeTick == 0) {
					continue;
				}
				
				if(System.currentTimeMillis() - clientLifeTick > CLIENT_LIFE) {
					if(client == localClient) {
						throw new RuntimeException("Local client should never die");
					}
					
					final Client disconnectedClient = clients.removeIndex(i);
					i -= 1;
					
					listener.onClientDisconnected(disconnectedClient);
					
					if(clients.size == 1) {
						listener.onDisconnected();
						connected = false;
					}
				}
			}
		}
		
		private void handleConnections() {
			final FileHandle[] roomClientHandles = roomClientsHandle.list();
			
			// Check if existing connections did not die
			tmpClientsArray.clear();
			for(final FileHandle clientHandle : roomClientHandles) {
				final Client existingClient = findClient(clientHandle.name());
				if(existingClient != null) {
					tmpClientsArray.add(existingClient);
				}
			}
			
			if(!clients.contains(localClient, true)) {
				throw new RuntimeException("Local client should never die");
			}
			
			clients.clear();
			clients.addAll(tmpClientsArray);
			
			// Check for new connections
			for(final FileHandle clientHandle : roomClientHandles) {
				final String clientId = clientHandle.name();
				final Client existingClient = findClient(clientId);
				
				if(existingClient == null) {
					final Client newClient = new Client(this, clientId);
					clients.add(newClient);
				}
			}
			
			if(clients.size == requiredConnections) {
				ready = true;
				listener.onRoomReady(this);
			}
		}
		
		public Client findClient(final String id) {
			for(int i = 0; i < clients.size; i += 1) {
				if(clients.get(i).getClientId().equals(id)) {
					return clients.get(i);
				}
			}
			return null;
		}
		
		public String getRoomId() {
			return roomId;
		}
		
		public void sendDataToAll(final byte[] data) {
			for(int i = 0; i < clients.size; i += 1) {
				final Client client = clients.get(i);
				if(client == localClient) {
					continue;
				}
				client.sendMessage(localClient, data);
			}
		}
		
		public void sendDataTo(final Client client, 
							   final byte[] data) {
			
			client.sendMessage(localClient, data);
		}
		
		public Client getLocalClient() {
			return localClient;
		}
		
		public Array<Client> getClients() {
			final Array<Client> clients = new Array<Client>(this.clients.size);
			clients.addAll(this.clients);
			return clients;
		}
	}
	
	private final int requiredConnections;
	private final FileHandle rootHandle;
	private final Listener listener;
	private final Room room;
	private long lastUpdateTime;
	private boolean disconnected;
	private long delay = MathUtils.random(0, 100);
	
	public DebugLocalMultiplayer(final String appId, 
							final int requiredConnections,
							final Listener listener) {
		
		this.requiredConnections = requiredConnections;
		this.listener = listener;
		
		rootHandle = Gdx.files.local("multiplayer/" + appId);
		rootHandle.mkdirs();
		
		room = findAvailableRoom();
	}
	
	public void disconnect() {
		listener.onDisconnected();
		disconnected = true;
	}

	/**
	 * Finds an existing room or creates a new one if it does not exists
	 * */
	private Room findAvailableRoom() {
		final FileHandle[] roomHandles = rootHandle.list();
		for(final FileHandle roomHandle : roomHandles) {
			final long roomTick = roomHandle.child("life.lf").lastModified();

			if(roomTick == 0) {
				continue;
			}
			
			if(System.currentTimeMillis() - roomTick < ROOM_LIFE) {
				return new Room(roomHandle.name());
			}
		}
		
		final String roomId = "Room" + roomHandles.length + 1;
		return new Room(roomId);
	}
	
	public void update() {
		if(disconnected) {
			return;
		}

		room.handleTick();
		
		final long time = System.currentTimeMillis();
		if(time - lastUpdateTime > delay) {
			delay = MathUtils.random(0, 100);
			lastUpdateTime = time;
			room.update();
		}
	}
	
	private byte[] forceReadBytes(final FileHandle fileHandle) {
		if(fileHandle.exists()) {
			while(!fileHandle.file().canRead()) {
				sleep();
			}
		}
		return fileHandle.readBytes();
	}
	
	private void forceWriteString(final FileHandle fileHandle, 
								  final String string,
								  final boolean append) {
		
		if(fileHandle.exists()) {
			while(!fileHandle.file().canWrite()) {
				sleep();
			}
		}
		fileHandle.writeString(string, append);
	}
	
	private void forceWriteBytes(final FileHandle fileHandle, 
								 final byte[] bytes, 
								 final boolean append) {
		
		if(fileHandle.exists()) {
			while(!fileHandle.file().canWrite()) {
				sleep();
			}
		}
		fileHandle.writeBytes(bytes, append);
	}
	
	private void sleep() {
		try {
			Thread.sleep(16);
		}
		catch(final Exception ex) {
			ex.printStackTrace();
		}
	}
}
