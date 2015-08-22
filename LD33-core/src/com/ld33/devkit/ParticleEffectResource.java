package com.ld33.devkit;

import patch.libgdx.ParticleEffect;
import patch.libgdx.ParticleEffectPool;
import patch.libgdx.ParticleEffectPool.PooledEffect;

import com.badlogic.gdx.Gdx;

public final class ParticleEffectResource implements Resource {

	private final ParticleEffect data;
	private final ParticleEffectPool pool;
	
	public ParticleEffectResource(final String fileName,
								  final AtlasResource atlas,
								  final Resolution resolution) {
		
		this(fileName, atlas, resolution, 1f);
	}
	
	public ParticleEffectResource(final String fileName,
								  final AtlasResource atlas,
								  final Resolution resolution,
								  final float scale) {
		
		if(fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		if(atlas == null) {
			throw new IllegalArgumentException("atlas cannot be null");
		}
		if(resolution == null) {
			throw new IllegalArgumentException("resolution cannot be null");
		}

		final ParticleEffectReader reader = new ParticleEffectReader(atlas.getAtlas());
		reader.setScale(resolution.getScale() * scale);

		this.data = reader.readParticleEffect(Gdx.files.internal("effects/" + fileName + ".fx"));
		this.pool = new ParticleEffectPool(data, 10, Integer.MAX_VALUE);
	}
	
	public ParticleEffect getData() {
		return data;
	}
	
	public PooledEffect getInstance() {
		return pool.obtain();
	}
	
	@Override
	public void dispose() {
		pool.clear();
	}
	
}
