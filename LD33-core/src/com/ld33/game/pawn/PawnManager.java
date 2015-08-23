package com.ld33.game.pawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.ManagerInterface;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.Tile;

public final class PawnManager implements ManagerInterface {

	private static final Vector2 tmpVector = new Vector2();
	
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
		
		for(int i = 0; i < Config.PlayerInitialMinions; i += 1) {
			final Minion minion = new Minion(app, player);
			player.registerMinion(minion);
		}
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
			if(Gdx.input.isKeyJustPressed(Config.MoveUpKey)) {
				player.startMoveUp();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveDownKey)) {
				player.startMoveDown();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveLeftKey)) {
				player.startMoveLeft();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveRightKey)) {
				player.startMoveRight();
			}
			
			if(!Gdx.input.isKeyPressed(Config.MoveUpKey)) {
				player.stopMoveUp();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveDownKey)) {
				player.stopMoveDown();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveLeftKey)) {
				player.stopMoveLeft();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveRightKey)) {
				player.stopMoveRight();
			}
		}

		final float tileWidth = app.getAssets().tileWidth;
		final float tileHeight = app.getAssets().tileHeight;
		
		// Move the player and check if movement was valid
		{
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
			
			player.moveBy(delta * Config.PlayerTilesPerSecond * tileWidth * player.getHorizontalMovementState(), 0f);
			
			if(checkCollision(leftTile, bottomTile, rightTile, topTile)) {
				player.setX(validX);
			}
			
			player.moveBy(0f, delta * Config.PlayerTilesPerSecond * tileHeight * player.getVerticalMovementState());

			if(checkCollision(leftTile, bottomTile, rightTile, topTile)) {
				player.setY(validY + player.getJumpDisplacement());
			}
		}
		
		// Move minions
		{
			for(int i = 0; i < player.getMinionCount(); i += 1) {
				final Minion minion = player.getMinion(i);
				
				if(minion.getState() != Minion.State.moving) {
					continue;
				}
				
				final float requiredAmountX = minion.calcTargetX() - minion.getX();
				final float requiredAmountY = minion.calcTargetY() - minion.getPlaneY();
				
				tmpVector.set(requiredAmountX, requiredAmountY);
				tmpVector.nor();
				
				final float amountX = delta * Config.MinionTilesPerSecond * tmpVector.x;
				final float amountY = delta * Config.MinionTilesPerSecond * tmpVector.y;
				
				final float scaleX = Math.min(Math.abs(amountX) / Math.abs(requiredAmountX), 1f);
				final float scaleY = Math.min(Math.abs(amountY) / Math.abs(requiredAmountY), 1f);
				
				minion.moveBy(
					requiredAmountX * scaleX,
					requiredAmountY * scaleY);
			}
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
		
		for(int i = 0; i < player.getMinionCount(); i += 1) {
			destination.addActor(player.getMinion(i));
		}
	}
	
}
