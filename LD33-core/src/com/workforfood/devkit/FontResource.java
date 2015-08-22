package com.workforfood.devkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public final class FontResource extends BitmapFont implements Resource {

	private float scale;
	
	public FontResource(final Resolution resolution,
						final AtlasResource atlas,
						final String fontName,
						final String regionName) {
		
		this(resolution, atlas, fontName, regionName, 1f);
	}
	
	public FontResource(final Resolution resolution,
						final AtlasResource atlas,
						final String fontName,
						final String regionName,
						final float scale) {

		super(Gdx.files.internal("fonts/" + resolution.name + "/" + fontName + ".fnt"), atlas.find(regionName));
		this.scale = resolution.getScale() * scale;
	}
	
	public float getScale() {
		return scale;
	}
	
}
