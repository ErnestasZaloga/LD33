package com.ld33.game.environment;

import com.ld33.game.pawn.PawnManager;

public class EnvironmentManager {
	
	private PawnManager pawnManager;
	private MapData mapData;
	
	public EnvironmentManager(PawnManager pawnManager, MapData mapData) {
		this.pawnManager = pawnManager;
		this.mapData = mapData;
	}
	
	public void update(float delta) {
		//Update each tower
		for(TileObject t : mapData.getTileObjects()) {
			if(distanceBetween(t.getX(), t.getY(), pawnManager.getPlayer().getX(), pawnManager.getPlayer().getY()) <= t.getRange()) {
				//Shoot at it TODOO
				t.attack(pawnManager.getPlayer().getX(), pawnManager.getPlayer().getY());
			}
		}
	}
	
	private float distanceBetween(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt((x1-x2)*(y1-y2));
	}
	
}
