package com.ld33.game.environment;

import com.badlogic.gdx.utils.Array;

public class MapData {
	
	private final Array<Tile> tiles;
	private final Array<TileObject> tileObjects;
	private final int mapW;
	private final int mapH;

	public MapData(final Array<Tile> tiles,
				   final int mapWidth,
				   final int mapHeight,
				   final Array<TileObject> tileObjects) {
		
		this.tiles = tiles;
		this.mapW = mapWidth;
		this.mapH = mapHeight;
		this.tileObjects = tileObjects;
	}
	
	public Array<Tile> getTiles() {
		return tiles;
	}
	
	public Array<TileObject> getTileObjects() {
		return tileObjects;
	}
	
	public Tile getTileAtXYIndex(int x, int y) {
		if(x < 0 || x >= mapW) {
			throw new RuntimeException("Invalid x: " + x + " for map width " + mapW);
		}
		if(y < 0 || y >= mapH) {
			throw new RuntimeException("Invalid y: " + y + " for map height " + mapH);
		}
		return tiles.get(y * mapW + x);
	}

	public int getMapW() {
		return mapW;
	}

	public int getMapH() {
		return mapH;
	}
	
}
