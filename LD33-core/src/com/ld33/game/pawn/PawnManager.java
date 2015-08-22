package com.ld33.game.pawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.ManagerInterface;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.Tile;

public final class PawnManager implements ManagerInterface {

	private final App app;
	private final Player player;
	private final MapData mapData;
	
	private float boundsWidth;
	private float boundsHeight;
	
	public PawnManager(final App app,
					   final MapData mapData) {
		
		this.app = app;
		this.mapData = mapData;
		
		this.player = new Player(app);
		
		final float tileWidth = app.getAssets().tileWidth;
		final float tileHeight = app.getAssets().tileHeight;
		
		player.setPosition(
				tileWidth * mapData.getStartX() + tileWidth / 2f - player.getWidth() / 2f,
				tileHeight * mapData.getStartY() + tileHeight / 2f - player.getHeight() / 2f);
	}
	
	public void setBounds(final float boundsWidth,
						  final float boundsHeight) {
		
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	private boolean checkCollision(final int leftTile,
								   final int bottomTile,
								   final int rightTile,
								   final int topTile) {
		
		final float playerX = player.getX();
		final float playerY = player.getPlaneY();
		final float playerWidth = player.getWidth();
		final float playerHeight = player.getHeight();
		
		for(int ix = leftTile; ix <= rightTile; ix += 1) {
			for(int iy = bottomTile; iy <= topTile; iy += 1) {
				final Tile tile = mapData.getTileAtXYIndex(ix, iy);
				
				if(!tile.isCollidable()) {
					continue;
				}

				final boolean horizontalCollision = playerX <= tile.getRight() && playerX + playerWidth >= tile.getX();
				final boolean verticalCollision = playerY <= tile.getTop() && playerY + playerHeight >= tile.getY();
				
				if(horizontalCollision && verticalCollision) {
					return true;
				}
			}
		}
	
		return false;
	}

	public void update(final float delta) {
		// Handle controls
		{
			if(Gdx.input.isKeyJustPressed(Config.MOVE_UP_KEY)) {
				player.startMoveUp();
			}
			if(Gdx.input.isKeyJustPressed(Config.MOVE_DOWN_KEY)) {
				player.startMoveDown();
			}
			if(Gdx.input.isKeyJustPressed(Config.MOVE_LEFT_KEY)) {
				player.startMoveLeft();
			}
			if(Gdx.input.isKeyJustPressed(Config.MOVE_RIGHT_KEY)) {
				player.startMoveRight();
			}
			
			if(!Gdx.input.isKeyPressed(Config.MOVE_UP_KEY)) {
				player.stopMoveUp();
			}
			if(!Gdx.input.isKeyPressed(Config.MOVE_DOWN_KEY)) {
				player.stopMoveDown();
			}
			if(!Gdx.input.isKeyPressed(Config.MOVE_LEFT_KEY)) {
				player.stopMoveLeft();
			}
			if(!Gdx.input.isKeyPressed(Config.MOVE_RIGHT_KEY)) {
				player.stopMoveRight();
			}
		}

		// Move the player and check if movement was valid
		{
			final float tileWidth = app.getAssets().tileWidth;
			final float tileHeight = app.getAssets().tileHeight;
			
			final float playerX = player.getX();
			final float playerY = player.getPlaneY();
			final float playerWidth = player.getWidth();
			final float playerHeight = player.getHeight();
			
			final int leftTile = (int)(playerX / tileWidth);
			final int rightTile = (int)((playerX + playerWidth) / tileWidth);
			final int bottomTile = (int)(playerY / tileHeight);
			final int topTile = (int)((playerY + playerHeight) / tileHeight);
			
			final float validX = player.getX();
			final float validY = player.getPlaneY();
			
			//X axis
			//System.out.println("Before x: " + player.getX() + " " + player.getY());
			player.moveBy(delta * Config.PLAYER_TPS * tileWidth * player.getHorizontalMovementState(), 0f);
			if(checkCollision(leftTile, bottomTile, rightTile, topTile)) {
				player.setX(validX);
			}
			System.out.println("Before y:" + " " + player.getY() +" "+ validY);
			//Y axis
			player.moveBy(0f, delta * Config.PLAYER_TPS * tileHeight * player.getVerticalMovementState());
			if(checkCollision(leftTile, bottomTile, rightTile, topTile)) {
				System.out.println("collision happened!");
				player.setY(validY + player.getJumpDisplacement());
			}
			System.out.println("After  y:" + " " + player.getY() +" "+ validY);
			//System.out.println("After xy: " + player.getX() + " " + player.getY());
		}
		
		// Limit player movement to within map bounds
		{
			if(player.getRight() > boundsWidth) {
				player.setX(boundsWidth - player.getWidth());
			}
			else if(player.getX() < 0f) {
				player.setX(0f);
			}
			
			// Special handling for y because of the animation
			final float playerY = player.getPlaneY();
			final float playerTop = playerY + player.getHeight();
			
			if(playerTop > boundsHeight) {
				player.setY(boundsHeight - player.getHeight() + player.getJumpDisplacement());
			}
			else if(playerY < 0f) {
				player.setY(player.getJumpDisplacement());
			}
		}
	}

	public void populate(final Group destination) {
		destination.addActor(player);
	}
	
}
