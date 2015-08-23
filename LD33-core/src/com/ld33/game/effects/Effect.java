package com.ld33.game.effects;

public class Effect {

	private float duration = 0;
	private float tick = 0.5f;
	private float timeUntilNextTick = 0;
	private boolean applyAction = true;
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
			applyAction = true;
			duration -= tick;
			if(duration <= 0) {
				finished = true;
			}
		}
	}
	
	public void tickComplete() {
		applyAction = false;
	}

	public boolean isFinished() {
		return finished;
	}
	
}
