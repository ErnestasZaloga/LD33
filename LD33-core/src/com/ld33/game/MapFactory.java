package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.Config;

public class MapFactory {
	
	private MapFactory() {
	}
	
	public static Array<Tile> generateMap(String mapString) {
		Array<Tile> tiles = new Array<Tile>();
		int x = 0;
		int y = Config.mapH;
		Tile t = null;
		for(int i=0; i<mapString.length(); i++) {
			if(mapString.charAt(i) == '#') {
				t = new Tile(mapString.charAt(i));
				t.setPosition(x*Config.tileWH, y*Config.tileWH);
				x++;
			} else if(mapString.charAt(i) == '.') {
				t = new Tile(mapString.charAt(i));
				t.setPosition(x*Config.tileWH, y*Config.tileWH);
				x++;
			} else if(mapString.charAt(i) == '\n') {
				x = 0;
				y--;
			}
			tiles.add(t);
		}
			
		return tiles;
	}
	
}
