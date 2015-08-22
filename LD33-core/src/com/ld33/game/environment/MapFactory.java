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
		
		int x = 0;
		int y = 0;
		
		for(int i = lines.length - 1; i >= 0; i -= 1) {
			final String line = lines[i];
			
			for(int ii = 0; ii < line.length(); ii += 1) {
				final char ch = line.charAt(ii);
				
				final TextureRegionExt region;
				Tile tile = null;
				
				if(ch == Config.WallTile) {
					region = app.getAssets().wallRegion;
					tile = new Tile(ch, region);
				}
				else if(ch == Config.GrassTile) {
					region = app.getAssets().grassTileRegion;
					tile = new Tile(ch, region);
				}
				else if(ch == Config.BoltTower) {
					region = app.getAssets().towerRegion;
					tile = new TileObject(ch, region);
					tileObjects.add((TileObject) tile);
				}
				else {
					throw new RuntimeException("Invalid map character found: " + ch);
				}
				
				tile.setPosition(x * tileWidth, y * tileHeight);
				tile.setxIndex(x);
				tile.setyIndex(y);
				
				x += 1;
				
				tiles.add(tile);
			}
			
			x = 0;
			y += 1;
		}
			
		return new MapData(tiles, mapWidth, mapHeight, tileObjects);
	}
	
}
