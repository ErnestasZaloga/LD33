package com.ld33.utils;

import patch.libgdx.ParticleEffect;
import patch.libgdx.ParticleEffectPool.PooledEffect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class PooledParticleEffectActor extends ParticleEffectActor implements ParticleEffectActor.OnCompletionListener, Poolable {

	public PooledParticleEffectActor() {
		super.setOnCompletionListener(this);
	}
	
	@Override
	public void setOnCompletionListener(final ParticleEffectActor.OnCompletionListener onCompletionListener) {
		throw new UnsupportedOperationException("Pooled particle effects cannot contain completion listeners");
	}
	
	@Override
	public void onCompletion(final ParticleEffectActor actor, 
						     final ParticleEffect effect) {
	
		remove();
	}
	
	@Override
	protected void setParent(final Group parent) {
		super.setParent(parent);
		if(parent == null) {
			Pools.free(this);
		}
	}
	
	@Override
	public void reset() {
		final ParticleEffect effect = getEffect();
		if(effect instanceof PooledEffect) {
			((PooledEffect) effect).free();
		}
		wake();
		setEffect(null);
		setOrigin(0f, 0f);
		setRotation(0f);
		setScale(1f);
		setPosition(0f, 0f);
		setSize(0f, 0f);
		setColor(Color.WHITE);

		clear();
	}
	
}
