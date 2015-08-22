package com.ld33.game.environment;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.workforfood.devkit.TextureRegionExt;

public class MapFactory {
	
	public static MapData generateMap(final App app,
									  final String mapString) {
		
		final String[] lines = mapString.split("\\r?\\n");
		
		final int mapWidth = lines[0].length();
		final int mapHeight = lines.length;
		
		final Array<Tile> tiles = new Array<Tile>();
		final Array<TileObject> tileObjects = new Array<TileObject>();
		
		final float tileWidth = app.getAssets().tileWidth;
		final float tileHeight = app.getAssets().tileHeight;
		
		int startX = -1;
		int startY = -1;
		
		for(int i = lines.length - 1, y = 0; i >= 0; i -= 1, y += 1) {
			final String line = lines[i];
			
			for(int ii = 0, x = 0; ii < line.length(); ii += 1, x += 1) {
				final char ch = line.charAt(ii);
				
				final TextureRegionExt region;
				Tile tile = null;
				
				if(ch == Config.WallTile) {
					region = app.getAssets().wallRegion;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.GrassTile) {
					region = app.getAssets().grassTileRegion;
					tile = new Tile(ch, x, y, false);
				}
				else if(ch == Config.BoltTower) {
					final TileObject tower = new TileObject(ch, x, y);
					
					region = app.getAssets().towerRegion;
					tile = tower;
					tileObjects.add(tower);
				}
				else if(ch == Config.PlayerStartPosition) {
					if(startX != -1 || startY != -1) {
						throw new RuntimeException("Player position already set");
					}
					
					startX = x;
					startY = y;
					
					region = app.getAssets().grassTileRegion;
					tile = new Tile(ch, x, y, false);
				}
				else {
					throw new RuntimeException("Invalid map character found: " + ch);
				}
				
				tile.setRegion(region);
				tile.setPosition(x * tileWidth, y * tileHeight);
				
				tiles.add(tile);
			}
		}

		if(startX == -1 || startY == -1) {
			throw new RuntimeException("Start position not found");
		}
		
		return new MapData(tiles, mapWidth, mapHeight, tileObjects, startX, startY);
	}
	
}
