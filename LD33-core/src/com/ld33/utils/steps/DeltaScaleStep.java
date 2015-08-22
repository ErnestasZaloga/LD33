package com.ld33.utils.steps;

public class DeltaScaleStep extends DelegateStep {

	public static DeltaScaleStep obtain() {
		return obtain(DeltaScaleStep.class);
	}
	
	public static DeltaScaleStep obtain(final float scale, 
								   		final Step step) {
		
		final DeltaScaleStep deltaScaleStep = Step.obtain(DeltaScaleStep.class);
		
		deltaScaleStep.scale = scale;
		deltaScaleStep.step = step;
		
		return deltaScaleStep;
	}
	
	protected float scale;
	
	public DeltaScaleStep() {
		this(1f, null);
	}
	
	public DeltaScaleStep(final float scale) {
		this(scale, null);
	}
	
	public DeltaScaleStep(final float scale, 
					 	  final Step step) {
		
		super(step);
		this.scale = scale;
	}

	@Override
	public DeltaScaleStep getPooledCopy() {
		return obtain(scale, step);
	}
	
	@Override
	public DeltaScaleStep getCopy() {
		return new DeltaScaleStep(scale, step);
	}
	
	@Override
	protected boolean delegate(final float delta, 
							   final Object object, 
							   final Step step) {
		
		if(step == null) {
			return true;
		}
		
		return step.perform(delta * scale, object);
	}
	
	public void setScale(final float scale) {
		this.scale = scale;
	}
	
	public float getScale() {
		return scale;
	}
	
	@Override
	public void reset() {
		super.reset();
		scale = 1f;
	}
	
}
