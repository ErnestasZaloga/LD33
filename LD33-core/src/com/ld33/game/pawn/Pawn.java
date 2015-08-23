package com.ld33.game.pawn;

import com.ld33.Config;
import com.ld33.utils.SpriteActor;
import com.ld33.utils.steps.FloatStep;
import com.ld33.utils.steps.Steps;

public abstract class Pawn extends SpriteActor {
	
	private final float depthHeightScaling;
	
	private float modY;
	private final FloatStep.Listener modYListener = new FloatStep.Listener() {
		@Override
		public void onChange(final FloatStep floatStep,
							 final float value) {
			
			modY = value;
		}
	};
	
	private final float maxHealth;
	private float health;
	
	public Pawn(final float maxHealth,
				final float depthHeightScaling) {
		
		this.maxHealth = maxHealth;
		this.depthHeightScaling = depthHeightScaling;
	}
	
	public float getMaxHealth() {
		return maxHealth;
	}
	
	public float getHealth() {
		return health;
	}
	
	public void damagePawn(final float damage) {
		this.health -= damage;
		
		if(health < 0f) {
			health = 0f;
		}
	}

	public float getPlaneY() {
		return getY() - (getHeight() * Config.PawnAnimationJumpHeight) * modY;
	}
	
	public float getCollisionHeight() {
		return getHeight() * depthHeightScaling;
	}
	
	public float getJumpDisplacement() {
		return getHeight() * Config.PawnAnimationJumpHeight * modY;
	}
	
	@Override
	public void act(final float delta) {
		final float jumpHeight = getHeight() * Config.PawnAnimationJumpHeight;
		
		// Important! process before calling act
		final float realY = getY() - jumpHeight * modY;
		
		super.act(delta);
		
		setY(realY + jumpHeight * modY);
	}
	
	protected void startMovementAnimation() {
		addAction(Steps.action(Steps.repeat(Steps.sequence(
				  Steps._float(0f, 1f, (Config.PawnAnimationJumpDuration / 2f), modYListener),
				  Steps._float(1f, 0f, (Config.PawnAnimationJumpDuration / 2f), modYListener)))));
	}
	
	protected void endMovementAnimation() {
		clearActions();
		addAction(Steps.action(Steps._float(modY, 0, modY * (Config.PawnAnimationJumpDuration / 2f), modYListener)));
	}
}
