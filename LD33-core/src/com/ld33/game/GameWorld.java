package com.ld33.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;
import com.ld33.game.pawn.PawnManager;

public class GameWorld extends Group {
	
	private App app;
	private MapData mapData;
	private PawnManager pawnManager;
	
	public GameWorld(App app) {
		this.app = app;
		mapData = new MapData(app);
		
		//Fill map with tiles
		for(Tile t : mapData.getTiles()) {
			addActor(t);
		}
		
		pawnManager = new PawnManager(app);
		pawnManager.setBounds(
				mapData.getMapH() * app.getAssets().tileRegion.getHeight(),
				mapData.getMapH() * app.getAssets().tileRegion.getHeight());
		
		pawnManager.populateWorld(this);
	}
	
	@Override
	public void act(final float delta) {
		super.act(delta);
		pawnManager.update(delta);
	}
	
}
