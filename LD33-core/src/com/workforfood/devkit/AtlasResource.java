package com.workforfood.devkit;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class AtlasResource implements Resource {

	private final TextureAtlas atlas;
	private final float scale;
	private final String atlasName;
	
	public AtlasResource(final Resolution resolution,
						 final String atlasName) {
		
		this.atlas = new TextureAtlas("textures/" + resolution.name + "/" + atlasName + "/" + atlasName + ".atlas");
		this.scale = resolution.getScale();
		this.atlasName = atlasName;
	}
	
	protected TextureAtlas getAtlas() {
		return atlas;
	}
	
	public String getName() {
		return atlasName;
	}
	
	public TextureRegionExt find(final String name) {
		return find(name, 1f);
	}
	
	public TextureRegionExt find(final String name, 
								 final float scale) {
		
		final TextureRegion region = atlas.findRegion(name);
		
		if(region == null) {
			throw new IllegalArgumentException("Cannot find region " + name + " in atlas " + atlasName);
		}
		
		return new TextureRegionExt(region, this.scale * scale);
	}
	
	@Override
	public void dispose() {
		atlas.dispose();
	}
	
}
