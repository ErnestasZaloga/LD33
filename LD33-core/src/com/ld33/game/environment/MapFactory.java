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
			boolean previousRegionVariant = i % 2 != 0;
			
			for(int ii = 0, x = 0; ii < line.length(); ii += 1, x += 1) {
				final char ch = line.charAt(ii);
				
				final TextureRegionExt region;
				Tile tile = null;
				
				if(ch == Config.WallTile) {
					final TileObject wall = new TileObject(ch, x, y);
					wall.setRegion(previousRegionVariant ? app.getAssets().wall1Region : app.getAssets().wall2Region);
					wall.setPosition(x * tileWidth, y * tileHeight);
					
					tileObjects.add(wall);
					
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.House) {
					final TileObject wall = new TileObject(ch, x, y);
					wall.setRegion(app.getAssets().houseRegion);
					wall.setPosition(x * tileWidth, y * tileHeight);
					
					tileObjects.add(wall);
					
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.Road) {
					region = app.getAssets().sandTileRegion;
					tile = new Tile(ch, x, y, false);
				}
				else if(ch == Config.GrassTile) {
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, false);
				}
				else if(ch == Config.BoltTower) {
					final TileObject tower = new TileObject(ch, x, y);
					tower.setRegion(app.getAssets().boltTowerRegion);
					tower.setPosition(x * tileWidth, y * tileHeight);
					
					tileObjects.add(tower);
					
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.SpawnerTower) {
					final TileObject tower = new TileObject(ch, x, y);
					tower.setRegion(app.getAssets().spawnTowerRegion);
					tower.setPosition(x * tileWidth, y * tileHeight);
					
					tileObjects.add(tower);
					
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.ElementalTower) {
					final TileObject tower = new TileObject(ch, x, y);
					tower.setRegion(app.getAssets().boltTowerRegion); //TODO select proper texture
					tower.setPosition(x * tileWidth, y * tileHeight);
					
					tileObjects.add(tower);
					
					region = previousRegionVariant ? app.getAssets().grassTile1Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, true);
				}
				else if(ch == Config.PlayerStartPosition) {
					if(startX != -1 || startY != -1) {
						throw new RuntimeException("Player position already set");
					}
					
					startX = x;
					startY = y;
					
					region = previousRegionVariant ? app.getAssets().grassTile2Region : app.getAssets().grassTile2Region;
					tile = new Tile(ch, x, y, false);
				}
				else {
					throw new RuntimeException("Invalid map character found: " + ch);
				}
				
				tile.setRegion(region);
				tile.setPosition(x * tileWidth, y * tileHeight);
				
				tiles.add(tile);
				previousRegionVariant = !previousRegionVariant;
			}
		}

		if(startX == -1 || startY == -1) {
			throw new RuntimeException("Start position not found");
		}
		
		return new MapData(tiles, mapWidth, mapHeight, tileObjects, startX, startY);
	}
	
}
