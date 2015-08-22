package com.ld33.game.environment;

import com.ld33.game.GameWorld;
import com.ld33.game.ManagerInterface;
import com.ld33.game.pawn.PawnManager;
import com.ld33.game.pawn.Player;

public class EnvironmentManager implements ManagerInterface {
	
	private final GameWorld gameWorld;
	private PawnManager pawnManager;
	private MapData mapData;
	
	public EnvironmentManager(final GameWorld gameWorld, PawnManager pawnManager, MapData mapData) {
		this.gameWorld = gameWorld;
		this.pawnManager = pawnManager;
		this.mapData = mapData;
	}
	
	@Override
	public void update(float delta) {
		final Player player = pawnManager.getPlayer();
		final float playerX = player.getX();
		final float playerY = player.getY();
		
		//Update each tower
		for(final TileObject tileObject : mapData.getTileObjects()) {
			final float towerX = tileObject.getX();
			final float towerY = tileObject.getY();
			
			if(distanceBetween(towerX, towerY, playerX, playerY) <= tileObject.getRange()) {
				if(tileObject.canAttack()) {
					tileObject.startCooldown();
					gameWorld.getProjectileManager().createBolt(towerX, towerY, playerX, playerY);
				}
			}
		}
	}
	
	private float distanceBetween(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt((x1-x2)*(y1-y2));
	}
	
}
