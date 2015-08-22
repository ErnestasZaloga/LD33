package com.ld33.game.environment;

import com.ld33.game.pawn.PawnManager;

public class EnvironmentManager {
	
	private PawnManager pawnManager;
	private MapData mapData;
	
	public EnvironmentManager(PawnManager pawnManager, MapData mapData) {
		this.pawnManager = pawnManager;
		this.mapData = mapData;
	}
	
	public void update() {
		System.out.println("update");
		//Update each tower
		for(TileObject t : mapData.getTileObjects()) {
			//Check if there is a pawn nearby TODO
			//Shoot at it TODO
		}
	}
	
}
