package com.ld33.game;

import com.ld33.utils.SpriteActor;
import com.workforfood.devkit.TextureRegionExt;

public class Tile extends SpriteActor {
	
	private char type;
	private TextureRegionExt textureRegionExt;
	
	public Tile(char type, TextureRegionExt textureRegionExt) {
		this.type = type;
		this.textureRegionExt = textureRegionExt;
		setRegion(textureRegionExt);
	}
	
}
