package com.ld33.game.environment;

import com.ld33.utils.SpriteActor;
import com.workforfood.devkit.TextureRegionExt;

public class Tile extends SpriteActor {
	
	protected char type;
	private TextureRegionExt textureRegionExt;
	private int xIndex;
	private int yIndex;
	
	public Tile(char type, TextureRegionExt textureRegionExt) {
		this.type = type;
		this.textureRegionExt = textureRegionExt;
		setRegion(textureRegionExt);
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}
	
}
