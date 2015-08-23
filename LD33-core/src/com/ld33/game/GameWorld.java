package com.ld33.game;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.game.environment.EnvironmentManager;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.MapFactory;
import com.ld33.game.environment.Tile;
import com.ld33.game.pawn.Pawn;
import com.ld33.game.pawn.PawnManager;
import com.ld33.game.pawn.Player;

public class GameWorld extends Group {
	
	private App app;
	private MapData mapData;
	private PawnManager pawnManager;
	private EnvironmentManager environmentManager;
	private ProjectileManager projectileManager;
	
	private final Array<Pawn> pawns = new Array<Pawn>();
	private final Array<Pawn> pawnBucket = new Array<Pawn>();
	
	private final Comparator<Pawn> pawnYComparator = new Comparator<Pawn>() {
		@Override
		public int compare(final Pawn a,
						   final Pawn b) {
			
			final float difference = a.getPlaneY() - b.getPlaneY();
			
			if(difference == 0) {
				return 0;
			}

			return difference > 0f ? 1 : -1;
		}
		
	};
	
	private final Array<ManagerInterface> managers = new Array<ManagerInterface>();
	protected final Group contentGroup = new Group();
	
	public GameWorld(App app) {
		this.app = app;
		
		setTransform(false);
		contentGroup.setTransform(false);
		contentGroup.setCullingArea(new Rectangle());
		
		addActor(contentGroup);
		
		mapData = MapFactory.generateMap(app, Gdx.files.internal("maps/map1.txt").readString());
		mapData.setTileWH(app.getAssets().tileWidth);
		
		contentGroup.setSize(
				mapData.getMapWidth() * app.getAssets().tileWidth,
				mapData.getMapHeight() * app.getAssets().tileHeight);
		
		// Fill map with tiles
		for(int iy = mapData.getMapHeight() - 1; iy >= 0; iy -= 1) {
			for(int ix = 0; ix < mapData.getMapWidth(); ix += 1) {
				final Tile tile = mapData.getTileAtXYIndexUnchecked(ix, iy);
				
				// Hack to fix the glitch with white lines
				tile.moveBy(-1, -1);
				tile.sizeBy(2, 2);
				
				contentGroup.addActor(tile);
			}
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
		
		for(final ManagerInterface manager : managers) {
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
			zSortVsTiles(player);
			
			for(int i = 0; i < player.getMinionCount(); i += 1) {
				zSortVsTiles(player.getMinion(i));
			}

			for(int i = 0; i < pawnManager.getEnemyMinionCount(); i += 1) {
				zSortVsTiles(pawnManager.getEnemyMinion(i));
			}
			
			zSortAllPawns();
		}
		
		// Update culling position
		contentGroup.getCullingArea().setPosition(-contentGroup.getX(), -contentGroup.getY());
	}

	private void zSortVsTiles(final Pawn pawn) {
		final int tileY = (int)(pawn.getPlaneY() / app.getAssets().tileHeight);
		pawn.setZIndex(mapData.getTileAtXYIndex(mapData.getMapWidth() - 1, tileY).getZIndex() + 1);
	}
	
	private void zSortAllPawns() {
		pawns.clear();
		
		final Player player = pawnManager.getPlayer();
		
		// Populate pawns array
		{
			pawns.add(player);
			
			for(int i = 0; i < player.getMinionCount(); i += 1) {
				pawns.add(player.getMinion(i));
			}
			
			for(int i = 0; i < pawnManager.getEnemyMinionCount(); i += 1) {
				pawns.add(pawnManager.getEnemyMinion(i));
			}
		}
		
		// If there's only one pawn there's no need to sort
		if(pawns.size <= 1) {
			return;
		}
		
		// Sort pawns by y coordinate. After sort 0 index is the pawn with lowest y
		pawns.sort(pawnYComparator);
		
		final float tileHeight = app.getAssets().tileHeight;
		int lastTileY = -1;
		
		for(int i = 0; i < pawns.size; i += 1) {
			final Pawn pawn = pawns.get(i);
			final int pawnTileY = (int)(pawn.getPlaneY() / tileHeight);
			
			if(pawnTileY != lastTileY) {
				zSortPawnBucket();
				lastTileY = pawnTileY;
			}
			
			pawnBucket.add(pawn);
		}
		
		zSortPawnBucket();
	}
	
	private void zSortPawnBucket() {
		if(pawnBucket.size <= 1) {
			pawnBucket.clear();
			return;
		}
		
		int minZIndex = Integer.MAX_VALUE;
		
		for(int i = 0; i < pawnBucket.size; i += 1) {
			final int zIndex = pawnBucket.get(i).getZIndex();
			
			if(zIndex < minZIndex) {
				minZIndex = zIndex;
			}
		}

		for(int i = 0; i < pawnBucket.size; i += 1) {
			pawnBucket.get(pawnBucket.size - i - 1).setZIndex(minZIndex + i);
		}
		
		int prevZIndex = Integer.MAX_VALUE;
		for(int i = 0; i < pawnBucket.size; i += 1) {
			if(pawnBucket.get(i).getZIndex() > prevZIndex) {
				throw new RuntimeException("Bad z indexing");
			}
			prevZIndex = pawnBucket.get(i).getZIndex();
		}
		
		pawnBucket.clear();
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
