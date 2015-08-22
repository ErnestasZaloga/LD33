package com.ld33.utils.steps;

import com.badlogic.gdx.math.Interpolation;

public class ChangingDeltaScaleStep extends TemporalStep {

	public static ChangingDeltaScaleStep obtain() {
		return obtain(ChangingDeltaScaleStep.class);
	}
	
	public static ChangingDeltaScaleStep obtain(final float scaleEnd,
												final float duration,
												final Step step) {
		
		return obtain(1f, scaleEnd, duration, null, step);
	}
	
	public static ChangingDeltaScaleStep obtain(final float scaleStart,
												final float scaleEnd,
												final float duration,
												final Step step) {
		
		return obtain(1f, scaleEnd, duration, null,  step);
	}
	
	public static ChangingDeltaScaleStep obtain(final float scaleEnd,
									    		final float duration, 
									    		final Interpolation interpolation,
									    		final Step step) {
		
		return obtain(1f, scaleEnd, duration, interpolation, step);
	}
	
	public static ChangingDeltaScaleStep obtain(final float scaleStart,
												final float scaleEnd,
												final float duration, 
												final Interpolation interpolation,
												final Step step) {
		
		final ChangingDeltaScaleStep dynamicDeltaScaleStep = Step.obtain(ChangingDeltaScaleStep.class);
		
		dynamicDeltaScaleStep.scaleStart = scaleStart;
		dynamicDeltaScaleStep.scaleEnd = scaleEnd;
		dynamicDeltaScaleStep.duration = duration;
		dynamicDeltaScaleStep.interpolation = interpolation;
		dynamicDeltaScaleStep.step = step;
		
		return dynamicDeltaScaleStep;
	}
	
	private Step step;
	private float scaleStart;
	private float scaleEnd;
	private float scale;
	
	@Override
	public ChangingDeltaScaleStep getPooledCopy() {
		return obtain(scaleStart, scaleEnd, duration, interpolation, step);
	}
	
	@Override
	public ChangingDeltaScaleStep getCopy() {
		final ChangingDeltaScaleStep step = new ChangingDeltaScaleStep();
		
		step.step = this.step;
		step.scaleStart = scaleStart;
		step.scaleEnd = scaleEnd;
		step.duration = duration;
		step.interpolation = interpolation;
		
		return step;
	}
	
	@Override
	protected void begin(final Object object) {
		scale = scaleStart;
	}

	@Override
	protected void update(final float delta,
						  final float percent,
						  final Object object) {
		
		scale = scaleStart + (scaleStart - scaleEnd) * percent;
		if(step != null) {
			step.perform(delta * scale, object);
		}
	}

	@Override
	public void free() {
		if(step != null) {
			step.free();
		}
		super.free();
	}
	
	@Override
	public void restart() {
		super.restart();
		
		if(step != null) {
			step.restart();
		}
	}

	@Override
	public void reset () {
		super.reset();
		step = null;
		scaleStart = 0f;
		scaleEnd = 0f;
		scale = 0f;
	}
	
	public void setStep(final Step step) {
		this.step = step;
	}
	
	public Step getStep() {
		return step;
	}
	
	public void setScaleStart(final float scaleStart) {
		this.scaleStart = scaleStart;
	}
	
	public float getScaleStart() {
		return scaleStart;
	}
	
	public void setScaleEnd(final float scaleEnd) {
		this.scaleEnd = scaleEnd;
	}
	
	public float getScaleEnd() {
		return scaleEnd;
	}
	
}
