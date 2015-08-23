package com.ld33.game.effects;

public class Effect {

	private float duration;
	private float tick = 1f;
	private float timeUntilNextTick = 0f;
	public boolean applyEffect = true;
	private boolean finished = false;
	
	private float slowMultiplier = 0;
	private float damageOverTick = 0;
	
	public Effect(float duration, float slowMultiplier, float damageOverTick) {
		this.duration = duration;
		this.slowMultiplier = slowMultiplier;
		this.damageOverTick = damageOverTick;
	}
	
	public void update(float delta) {
		if(finished) return;
		
		timeUntilNextTick -= delta;
		if(timeUntilNextTick < 0) {
			timeUntilNextTick = 0;
			applyEffect = true;
			duration -= tick;
		}
		if(duration <= 0) {
			finished = true;
			slowMultiplier = 1f;
		}
	}
	
	public void tickComplete() {
		applyEffect = false;
	}

	public boolean isFinished() {
		return finished;
	}

	public float getSlowMultiplier() {
		return slowMultiplier;
	}

	public float getDamageOverTick() {
		return damageOverTick;
	}
	
}
