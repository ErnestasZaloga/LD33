package com.workforfood.devkit;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class TextureRegionExt extends TextureRegion {
	
	private float width;
	private float height;
	
	public TextureRegionExt(final TextureRegion region) {
		this(region, 1f);
	}

	public TextureRegionExt(final TextureRegion region,
							final float scale) {
		
		super(region);
		
		if(region != null) {
			setSize(region.getRegionWidth() * scale, region.getRegionHeight() * scale);
		}
	}
	
	public TextureRegionExt(final TextureRegionExt region) {
		this(region, 1f);
	} 
	
	public TextureRegionExt(final TextureRegionExt region,
							final float scale) {
		
		super(region);
		
		if(region != null) {
			setSize(region.getWidth() * scale, region.getHeight() * scale);
		}
	}
	
	public void setWidth(final float width) {
		this.width = width;
	}
	
	public void setHeight(final float height) {
		this.height = height;
	}
	
	public void setSize(final float width,
						final float height) {
		
		this.width = width;
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
}
