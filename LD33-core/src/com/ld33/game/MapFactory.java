package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.workforfood.devkit.TextureRegionExt;

public class MapFactory {
	
	private MapFactory() {
	}
	
	public static Array<Tile> generateMap(final App app, String mapString) {
		Array<Tile> tiles = new Array<Tile>();
		int x = 0;
		int y = 1;
		Tile t = null;
		
		for(int i=0; i<mapString.length(); i++) {
			
		}
		
		for(int i=0; i<mapString.length(); i++) {
			final char ch = mapString.charAt(i);
			
			if(ch == '\n') {
				x = 0;
				y -= 1;
				continue;
			}
			
			final TextureRegionExt region;
			
			if(mapString.charAt(i) == '#') {
			}
			else if(mapString.charAt(i) == '.') {
			
			}
			
			t = new Tile(region);
			t.setPosition(x*Config.tileWH, y*Config.tileWH);
			x++;
			
			tiles.add(t);
		}
			
		return tiles;
	}
	
}
