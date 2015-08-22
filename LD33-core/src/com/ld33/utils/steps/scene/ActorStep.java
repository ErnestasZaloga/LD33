package com.ld33.utils.steps.scene;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.ld33.utils.steps.TypeStep;

abstract public class ActorStep extends TypeStep<Actor> {

	public ActorStep() {
		this(null);
	}
	
	public ActorStep(final Actor actor) {
		super(actor);
	}
	
}
