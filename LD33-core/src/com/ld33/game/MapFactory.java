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
		int y = 4;
		Tile t = null;
		float w = app.getAssets().GrassRegion.getWidth();
		float h = app.getAssets().GrassRegion.getHeight();
		
		char input = ' ';
		int mapW = 0;
		while(input != '\n') {
			//mapString.charAt(index);
			break;
		}
		
		for(int i=0; i<mapString.length(); i++) {
			final char ch = mapString.charAt(i);
			
			if(ch == '\n') {
				x = 0;
				y--;
				continue;
			}
			
			TextureRegionExt region = null;
			
			if(mapString.charAt(i) == '#') {
				region = app.getAssets().WallRegion;
			}
			else if(mapString.charAt(i) == '.') {
				region = app.getAssets().GrassRegion;
			}
			
			t = new Tile(mapString.charAt(i), region);
			t.setPosition(x*w, y*h);
			x++;
			
			tiles.add(t);
		}
			
		return tiles;
	}
	
}
