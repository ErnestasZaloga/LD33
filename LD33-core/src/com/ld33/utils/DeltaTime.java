package com.ld33.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.WindowedMean;

public final class DeltaTime {

	private float rawDeltaTimeScale;
	private float current;
	private final WindowedMean mean;
	
	public DeltaTime(final int smoothing) {
		mean = new WindowedMean(smoothing);
	}
	
	public void update() {
		final float rawDeltaTime = Gdx.graphics.getRawDeltaTime() * rawDeltaTimeScale;
		mean.addValue(rawDeltaTime);
		rawDeltaTimeScale = 1f;
		
		current = mean.hasEnoughData() ? mean.getMean() : rawDeltaTime;
	}
	
	public float get() {
		return current;
	}
	
	public void reset() {
		mean.clear();
		current = 0f;
		rawDeltaTimeScale = 0f;
	}
	
}
