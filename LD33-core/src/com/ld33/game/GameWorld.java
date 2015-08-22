package com.ld33.game;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;
import com.ld33.game.environment.EnvironmentManager;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.Tile;
import com.ld33.game.pawn.PawnManager;

public class GameWorld extends Group {
	
	private App app;
	private MapData mapData;
	private PawnManager pawnManager;
	private EnvironmentManager environmentManager;
	private ProjectileManager projectileManager;
	
	public GameWorld(App app) {
		this.app = app;
		mapData = new MapData(app);
		
		//Fill map with tiles
		for(Tile t : mapData.getTiles()) {
			addActor(t);
		}
		
		pawnManager = new PawnManager(app);
		pawnManager.setBounds(
				mapData.getMapW() * app.getAssets().tileWidth,
				mapData.getMapH() * app.getAssets().tileHeight);
		
		pawnManager.populateWorld(this);
		
		environmentManager = new EnvironmentManager(pawnManager, mapData);
		projectileManager = new ProjectileManager(app);
	}
	
	@Override
	public void act(final float delta) {
		super.act(delta);
		pawnManager.update(delta);
		environmentManager.update(delta);
	}
	
	public ProjectileManager getProjectileManager() {
		return projectileManager;
	}
	
}
