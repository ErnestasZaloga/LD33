package com.ld33.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

public class FrameBufferExt extends FrameBuffer {

	private final TextureRegion region = new TextureRegion();
	
	public FrameBufferExt(final Pixmap.Format format, 
						  final int width, 
						  final int height, 
						  final boolean hasDepth) {
		
		super(format, width, height, hasDepth);
		
		region.setTexture(getColorBufferTexture());
		region.setRegion(0, 0, width, height);
		region.flip(false, true);
	}
	
	public TextureRegion getRegion() {
		region.setTexture(getColorBufferTexture());
		
		return region;
	}
	
}
