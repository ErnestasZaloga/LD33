package com.ld33.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.game.environment.EnvironmentManager;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.MapFactory;
import com.ld33.game.environment.Tile;
import com.ld33.game.pawn.PawnManager;
import com.ld33.game.pawn.Player;

public class GameWorld extends Group {
	
	private App app;
	private MapData mapData;
	private PawnManager pawnManager;
	private EnvironmentManager environmentManager;
	private ProjectileManager projectileManager;
	
	private final Array<ManagerInterface> managers = new Array<ManagerInterface>();
	protected final Group contentGroup = new Group();
	
	public GameWorld(App app) {
		this.app = app;
		
		setTransform(false);
		contentGroup.setTransform(false);
		contentGroup.setCullingArea(new Rectangle());
		
		addActor(contentGroup);
		
		mapData = MapFactory.generateMap(app, Gdx.files.internal("maps/map1.txt").readString());
		
		contentGroup.setSize(
				mapData.getMapWidth() * app.getAssets().tileWidth,
				mapData.getMapHeight() * app.getAssets().tileHeight);
		
		// Fill map with tiles
		final Array<Tile> tiles = mapData.getTiles();
		for(int i = tiles.size - 1; i >= 0; i -= 1) {
			final Tile tile = tiles.get(i);
			
			// Hack to fix the glitch with white lines
			tile.moveBy(-1, -1);
			tile.sizeBy(2, 2);
			
			contentGroup.addActor(tile);
		}
		
		// Init managers
		
		pawnManager = new PawnManager(app, mapData);
		pawnManager.setBounds(contentGroup.getWidth(), contentGroup.getHeight());
		pawnManager.populate(contentGroup);
		
		environmentManager = new EnvironmentManager(this, pawnManager, mapData);
		projectileManager = new ProjectileManager(app, this);
		
		managers.add(pawnManager);
		managers.add(environmentManager);
		managers.add(projectileManager);
	}
	
	@Override
	public void act(final float delta) {
		super.act(delta);
		
//		pawnManager.update(delta);
//		environmentManager.update(delta);
		for(ManagerInterface manager : managers) {
			manager.update(delta);
		}
		
		final Player player = pawnManager.getPlayer();
		
		// Animated view scrolling
		{
			final float viewX = contentGroup.getX();
			final float viewY = contentGroup.getY();
			
			final float viewTargetX = -(player.getX() - (getWidth() / 2f - player.getWidth() / 2f));
			final float viewTargetY = -(player.getPlaneY() - (getHeight() / 2f - player.getHeight() / 2f));
			
			final float percent = delta / 0.3f;
			
			contentGroup.setPosition(
					viewX + (viewTargetX - viewX) * percent,
					viewY + (viewTargetY - viewY) * percent);
		}

		// Limit view
		{
			final float minViewX = -(contentGroup.getWidth() - getWidth());
			final float maxViewX = 0f;
			
			final float minViewY = -(contentGroup.getHeight() - getHeight());
			final float maxViewY = 0f;
			
			if(contentGroup.getX() > maxViewX) {
				contentGroup.setX(maxViewX);
			}
			else if(contentGroup.getX() < minViewX) {
				contentGroup.setX(minViewX);
			}
			
			if(contentGroup.getY() > maxViewY) {
				contentGroup.setY(maxViewY);
			}
			else if(contentGroup.getY() < minViewY) {
				contentGroup.setY(minViewY);
			}
		}
		
		// Z sorting
		{
			final int rightTileX = (int)(player.getX() / app.getAssets().tileWidth);
			final int tileY = (int)((player.getPlaneY()) / app.getAssets().tileHeight);

//			player.setZIndex(mapData.getTileAtXYIndex(rightTileX, tileY).getZIndex() + 1);
		}
		
		// Update culling position
		contentGroup.getCullingArea().setPosition(-contentGroup.getX(), -contentGroup.getY());
	}
	
	@Override
	public void setSize(final float width,
						final float height) {
		
		super.setSize(width, height);
		contentGroup.getCullingArea().setSize(width, height);
	}
	
	public PawnManager getPawnManager() {
		return pawnManager;
	}
	
	public ProjectileManager getProjectileManager() {
		return projectileManager;
	}
	
}
