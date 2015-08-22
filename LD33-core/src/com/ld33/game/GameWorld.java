package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;

public class GameWorld {
	
	private App app;
	private Array<Tile> tiles;
	
	public GameWorld(App app) {
		this.app = app;
		tiles = new Array<Tile>();
	}
	
	public void update(float delta) {
		
	}
	
}
