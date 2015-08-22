package com.ld33.devkit;

import patch.libgdx.ParticleEffect;
import patch.libgdx.ParticleEmitter;
import patch.libgdx.ParticleEmitter.RangedNumericValue;
import patch.libgdx.ParticleEmitter.ScaledNumericValue;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectReader {

	private final TextureAtlas atlas;
	private float scale;
	
	public ParticleEffectReader(final TextureAtlas atlas) {
		this(atlas, 1f);
	}
	
	public ParticleEffectReader(final TextureAtlas atlas, 
								final float scale) {
		
		if(atlas == null) {
			throw new IllegalArgumentException("atlas cannot be null.");
		}
		
		this.atlas = atlas;
		this.scale = scale;
	}
	
	public void setScale(final float scale) {
		this.scale = scale;
	}
	
	public float getScale() {
		return scale;
	}
	
	public ParticleEffect readParticleEffect(final FileHandle file) {
		final ParticleEffect effect = new ParticleEffect();
		effect.load(file, atlas);
		applyScale(effect);
		
		return effect;
	}
	
	private void applyScale(final ParticleEffect effect) {
		final Array<ParticleEmitter> emitters = effect.getEmitters();
		
		for(final ParticleEmitter emitter : emitters) {
			final ScaledNumericValue emitterScale = emitter.getScale();
			emitterScale.setHigh(emitterScale.getHighMin() * scale, emitterScale.getHighMax() * scale);
			emitterScale.setLow(emitterScale.getLowMin() * scale, emitterScale.getLowMax() * scale);
			
			final ScaledNumericValue emitterVelocity = emitter.getVelocity();
			emitterVelocity.setHigh(emitterVelocity.getHighMin() * scale, emitterVelocity.getHighMax() * scale);
			emitterVelocity.setLow(emitterVelocity.getLowMin() * scale, emitterVelocity.getLowMax() * scale);
			
			final RangedNumericValue offsetX = emitter.getXOffsetValue();
			offsetX.setLow(offsetX.getLowMin() * scale, offsetX.getLowMax() * scale);
			
			final RangedNumericValue offsetY = emitter.getYOffsetValue();
			offsetY.setLow(offsetY.getLowMin() * scale, offsetY.getLowMax() * scale);
		
			final ScaledNumericValue spawnWidth = emitter.getSpawnWidth();
			spawnWidth.setLow(spawnWidth.getLowMin() * scale, spawnWidth.getLowMax() * scale);
			spawnWidth.setHigh(spawnWidth.getHighMin() * scale, spawnWidth.getHighMax() * scale);
		
			final ScaledNumericValue spawnHeight = emitter.getSpawnHeight();
			spawnHeight.setLow(spawnHeight.getLowMin() * scale, spawnHeight.getLowMax() * scale);
			spawnHeight.setHigh(spawnHeight.getHighMin() * scale, spawnHeight.getHighMax() * scale);
		}
	}
	
}
