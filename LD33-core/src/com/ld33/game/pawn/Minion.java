package com.ld33.game.pawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ld33.App;
import com.ld33.Config;

public final class Minion extends Pawn {
	
	private static final Vector2 tmpVector = new Vector2();
	
	public static enum State {
		idle,
		moving;
	}
	
	private final App app;
	private final Player player;
	
	private State state = State.idle;
	private float targetX;
	private float targetY;
	private float delayTimer;
	private float delay;
	
	public Minion(final App app,
				  final Player player) {
		
		super(Config.MinionMaxHealth, app.getAssets().depthHeightScaling);
		
		this.app = app;
		this.player = player;
		
		setRegion(app.getAssets().minionRegion);
	}
	
	public float calcTargetX() {
		return (player.getX() + player.getWidth() / 2f) + targetX;
	}
	
	public float calcTargetY() {
		return (player.getPlaneY() + player.getHeight() / 2f) + targetY;
	}
	
	public void begin() {
		chooseTarget();
		setPosition(calcTargetX(), calcTargetY());
		
		clearActions();
		state = State.idle;
		
		delaySetup();
		delayTimer = 0f;
	}
	
	public State getState() {
		return state;
	}

	@Override
	public void act(final float delta) {
		super.act(delta);
		
		if(state == State.idle) {
			delayTimer += delta;
			
			if(delayTimer >= delay) {
				delayTimer = 0f;
				delaySetup();

				chooseTarget();
				startMovement();
			}
		}
		else if(state == State.moving) {
			if(targetX == getX() && targetY == getY()) {
				stopMovement();
			}
		}
		else {
			throw new RuntimeException("invalid minion state");
		}
	}
	
	private void chooseTarget() {
		final float radiusOffset = MathUtils.random(Config.MinionPositionRadiusMin, 1f);
		final float radiusAngle = MathUtils.random(0f, 360f);
		final float radius = player.getMinionRadius();

		tmpVector.set(0f, radius * radiusOffset);
		tmpVector.rotate(radiusAngle);
		
		targetX = tmpVector.x;
		targetY = tmpVector.y;
	}
	
	private void startMovement() {
		state = State.moving;
		startMovementAnimation();
	}
	
	private void stopMovement() {
		System.out.println("Switch to idle");
		state = State.idle;
		endMovementAnimation();
	}
	
	private void delaySetup() {
		delay = MathUtils.random(Config.MinionReactionDelayMin, Config.MinionReactionDelayMax);
	}
	
}
