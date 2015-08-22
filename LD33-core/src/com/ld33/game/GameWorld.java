package com.ld33.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;

public class GameWorld extends Group {
	
	private App app;
	private MapData mapData;
	
	public GameWorld(App app) {
		this.app = app;
		mapData = new MapData(app);
		
		//Fill map with tiles
		for(Tile t : mapData.getTiles()) {
			app.getStage().addActor(t);
		}
	}
	
}
