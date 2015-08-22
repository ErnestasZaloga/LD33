package com.ld33.game.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;

public class MapData {
	
	private Array<Tile> tiles;
	private Array<TileObject> tileObjects = new Array<TileObject>();
	private final int mapW;
	private final int mapH;

	public MapData(App app) {
		tiles = MapFactory.generateMap(app, Gdx.files.internal("maps/map1.txt").readString(), tileObjects);
		mapW = MapFactory.getMapWidth(Gdx.files.internal("maps/map1.txt").readString());
		mapH = MapFactory.getMapHeight(Gdx.files.internal("maps/map1.txt").readString());
	}
	
	public Array<Tile> getTiles() {
		return tiles;
	}
	
	public Array<TileObject> getTileObjects() {
		return tileObjects;
	}
	
	public Tile getTileAtXYIndex(int x, int y) {
		for(Tile t : tiles) {
			if(t.getxIndex() == x && t.getyIndex() == y) {
				return t;
			}
		}
		return null;
	}

	public int getMapW() {
		return mapW;
	}

	public int getMapH() {
		return mapH;
	}
	
}
