package com.ld33.game.environment;

import com.ld33.utils.SpriteActor;

public class Tile extends SpriteActor {
	
	protected final char type;
	private final int xIndex;
	private final int yIndex;
	private final boolean collidable;
	
	public Tile(final char type,
				final int xIndex,
				final int yIndex,
				final boolean collidable) {
		
		this.type = type;
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.collidable = collidable;
	}

	public int getXIndex() {
		return xIndex;
	}

	public int getYIndex() {
		return yIndex;
	}

	public boolean isCollidable() {
		return collidable;
	}
	
}
