package com.ld33.utils.steps.scene;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.utils.steps.Step;

public class TransformStep extends ActorStep {
	
	public static TransformStep obtain() {
		return obtain(TransformStep.class);
	}
	
	public static TransformStep obtain(final boolean transform) {
		return obtain(transform, null);
	}
	
	public static TransformStep obtain(final boolean transform,
									   final Actor actor) {
		
		final TransformStep step = Step.obtain(TransformStep.class);
		step.object = actor;
		step.transform = transform;
		
		return step;
	}
	
	private boolean transform;
	
	public TransformStep() {
		this(null, false);
	}
	
	public TransformStep(final Actor actor) {
		this(actor, false);
	}
	
	public TransformStep(final Actor actor,
					     final boolean transform) {
		
		super(actor);
		this.transform = transform;
	}
	
	@Override
	public TransformStep getPooledCopy() {
		return obtain(transform, object);
	}
	
	@Override
	public TransformStep getCopy() {
		return new TransformStep(object, transform);
	}
	
	public void setTransform(final boolean transform) {
		this.transform = transform;
	}
	
	public boolean getTransform() {
		return transform;
	}
	
	@Override
	public boolean perform(final float delta, 
						   final Object rawObject,
						   final Actor actor) {
		
		if(actor instanceof Group) {
			((Group) actor).setTransform(transform);
		}
		return true;
	}
	
	@Override
	public void reset() {
		super.reset();
		transform = false;
	}
	
}
