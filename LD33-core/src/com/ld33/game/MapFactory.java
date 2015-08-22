package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.workforfood.devkit.TextureRegionExt;

public class MapFactory {
	
	private MapFactory() {
	}
	
	public static Array<Tile> generateMap(final App app, String mapString) {
		Array<Tile> tiles = new Array<Tile>();
		
		int x = 0;
		int y = getMapHeight(mapString)-1;
		Tile t = null;
		float w = app.getAssets().grassTileRegion.getWidth();
		float h = app.getAssets().grassTileRegion.getHeight();
		
		for(int i=0; i<mapString.length(); i++) {
			final char ch = mapString.charAt(i);
			
			if(ch == '\n') {
				x = 0;
				y--;
				continue;
			}
			
			TextureRegionExt region = null;
			
			if(mapString.charAt(i) == '#') {
				region = app.getAssets().wallRegion;
			}
			else if(mapString.charAt(i) == '.') {
				region = app.getAssets().grassTileRegion;
			}
			
			t = new Tile(mapString.charAt(i), region);
			t.setPosition(x*w, y*h);
			t.setxIndex(x);
			t.setyIndex(y);
			x++;
			
			tiles.add(t);
		}
			
		return tiles;
	}
	
	public static int getMapWidth(String mapString) {
		char input = mapString.charAt(0);
		int mapW = 0;
		while(true) {
			input = mapString.charAt(mapW);
			if(input == '\n') {
				break;
			}
			mapW++;
		}
		return mapW;
	}
	
	public static int getMapHeight(String mapString) {
		char input = mapString.charAt(0);
		int mapW = 0;
		int mapH = 0;
		while(true) {
			input = mapString.charAt(mapW);
			if(input == '\n') {
				break;
			}
			mapW++;
		}
		mapH = mapString.length()/mapW;
		return mapH;
	}
	
}
