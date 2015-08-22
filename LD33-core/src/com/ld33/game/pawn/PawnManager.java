package com.ld33.game.pawn;

import com.badlogic.gdx.Gdx;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.GameWorld;

public final class PawnManager {

	private final App app;
	private final Player player;
	
	private float boundsWidth;
	private float boundsHeight;
	
	public PawnManager(final App app) {
		this.app = app;
		this.player = new Player(app);
	}
	
	public void setBounds(final float boundsWidth,
						  final float boundsHeight) {
		
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void update(final float delta) {
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
		
		player.moveBy(
				delta * Config.PLAYER_TPS * app.getAssets().tileWidth * player.getHorizontalMovementState(),
				delta * Config.PLAYER_TPS * app.getAssets().tileHeight * player.getVerticalMovementState());
		
		if(player.getRight() > boundsWidth) {
			player.setX(boundsWidth - player.getWidth());
		}
		else if(player.getX() < 0f) {
			player.setX(0f);
		}
		
		if(player.getTop() > boundsHeight) {
			player.setY(boundsHeight - player.getHeight());
		}
		else if(player.getY() < 0f) {
			player.setY(0f);
		}
	}
	
	public void populateWorld(final GameWorld world) {
		world.addActor(player);
	}
	
}
