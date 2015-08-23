package com.ld33.game.environment;

import com.badlogic.gdx.utils.Array;

public class MapData {
	
	private final Array<Tile> tiles;
	private final Array<TileObject> tileObjects;
	private final int mapWidth;
	private final int mapHeight;
	private final int startX;
	private final int startY;

	public MapData(final Array<Tile> tiles,
				   final int mapWidth,
				   final int mapHeight,
				   final Array<TileObject> tileObjects,
				   final int startX,
				   final int startY) {
		
		this.tiles = tiles;
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
		this.tileObjects = tileObjects;
		this.startX = startX;
		this.startY = startY;
	}
	
	public Array<TileObject> getTileObjects() {
		return tileObjects;
	}
	
	public Tile getTileAtXYIndexUnchecked(final int x,
										  final int y) {
		
		return tiles.get(y * mapWidth + x);
	}
	
	public Tile getTileAtXYIndex(final int x,
								 final int y) {
		
		if(x < 0 || x >= mapWidth) {
			throw new RuntimeException("Invalid x: " + x + " for map width " + mapWidth);
		}
		if(y < 0 || y >= mapHeight) {
			throw new RuntimeException("Invalid y: " + y + " for map height " + mapHeight);
		}
		
		return tiles.get(y * mapWidth + x);
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}
	
}
