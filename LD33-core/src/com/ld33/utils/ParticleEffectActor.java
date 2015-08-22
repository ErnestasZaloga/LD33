package com.ld33.utils;

import patch.libgdx.ParticleEffect;
import patch.libgdx.ParticleEmitter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ParticleEffectActor extends Actor {
	public static interface OnCompletionListener {
		public void onCompletion(final ParticleEffectActor actor, 
								 final ParticleEffect effect);
	}
	
	private float lastX = Float.MIN_VALUE;
	private float lastY = Float.MIN_VALUE;
	
	private float[] effectColorArray;
	private float[] effectColorTimelineArray;
	
	private ParticleEffect effect;
	private OnCompletionListener listener;
	private float delta;
	private boolean sleeping;
	
	public ParticleEffectActor() {
		this(null);
	}
	
	public ParticleEffectActor(final ParticleEffect effect) {
		this.effect = effect;
	}
	
	public void setOnCompletionListener(final OnCompletionListener listener) {
		this.listener = listener;
	}
	
	public OnCompletionListener getOnCompletionListener() {
		return listener;
	}
	
	public void setEffect(final ParticleEffect effect) {
		this.effect = effect;
		lastX = Float.MIN_VALUE;
		lastY = Float.MIN_VALUE;
	}
	
	public ParticleEffect getEffect() {
		return effect;
	}
	
	public void sleep() {
		sleeping = true;
	}
	
	public void wake() {
		sleeping = false;
	}
	
	public boolean isSleeping() {
		return sleeping;
	}
	
	public void start() {
		effect.start();
	}
	
	public void reset() {
		effect.reset();
	}
	
	public void allowCompletion() {
		effect.allowCompletion();
	}
	
	public boolean isComplete() {
		return effect.isComplete();
	}
	
	public void setDuration(final int duration) {
		effect.setDuration(duration);
	}

	public void setFlip(final boolean flipX, 
						final boolean flipY) {
		
		effect.setFlip(flipX, flipY);
	}

	public void flipY() {
		effect.flipY();
	}

	public Array<ParticleEmitter> getEmitters() {
		return effect.getEmitters();
	}

	public ParticleEmitter findEmitter(final String name) {
		return effect.findEmitter(name);
	}
	
	/**
	 * DONT USE IT ON SHARED EFFECTS!
	 * */
	public void setEffectColor(final Color color) {
		if(effect == null) {
			throw new IllegalStateException("effect is null");
		}
		
		if(effectColorArray == null) {
			effectColorArray = new float[] { 0f, 0f, 0f };
			effectColorTimelineArray = new float[] { 0f };
		}
		
		effectColorArray[0] = color.r;
		effectColorArray[1] = color.g;
		effectColorArray[2] = color.b;
		
		final Array<ParticleEmitter> emitters = effect.getEmitters();
		for(int i = 0; i < emitters.size; i += 1) {
			final ParticleEmitter emitter = emitters.get(i);
			final ParticleEmitter.GradientColorValue gradient = emitter.getTint();
			
			gradient.setTimeline(effectColorTimelineArray);
			gradient.setColors(effectColorArray);
		}
	}
	
	@Override
	public void act(final float delta) {
		super.act(delta);
		this.delta = delta;
		
		if(effect == null || sleeping) {
			return;
		}
		
		if(effect.isComplete()) {
			if(listener != null) {
				listener.onCompletion(this, effect);
			}
		}
	}
	
	@Override
	public void draw(final Batch batch, 
					 final float parentAlpha) {
		
		if(effect == null || sleeping) {
			return;
		}
		
		final Color color = getColor();
		batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		
		final float x = getX() + getWidth() / 2f;
		final float y = getY() + getHeight() / 2f;
		
		if(x != lastX || y != lastY) {
			lastX = x;
			lastY = y;
			
			effect.setPosition(x, y);
		}
		
		effect.update(delta);
		effect.draw(batch);
		delta = 0f;
	}
	
}