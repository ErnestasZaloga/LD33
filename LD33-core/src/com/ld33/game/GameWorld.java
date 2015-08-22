package com.ld33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;

public class GameWorld extends Group {
	
	private App app;
	private Array<Tile> tiles;
	
	public GameWorld(App app) {
		this.app = app;
		tiles = MapFactory.generateMap(Gdx.files.internal("maps/map1.txt").readString());
		for(Tile t : tiles) {
			app.getStage().addActor(t);
		}
	}
	
}
