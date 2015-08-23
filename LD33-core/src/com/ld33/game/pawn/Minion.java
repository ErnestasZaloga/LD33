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
	private float movementSpeedScaler;
	
	private float cachedPlayerX;
	private float cachedPlayerY;
	
	public Minion(final App app,
				  final Player player) {
		
		super(Config.MinionMaxHealth);
		
		this.app = app;
		this.player = player;
		
		setRegion(app.getAssets().minionRegion);
	}
	
	protected void cachePlayerPosition() {
		cachedPlayerX = player.getX();
		cachedPlayerY = player.getPlaneY();
	}
	
	protected float getCachedPlayerX() {
		return cachedPlayerX;
	}
	
	protected float getCachedPlayerY() {
		return cachedPlayerY;
	}
	
	public float getMovementSpeedScaler() {
		return movementSpeedScaler;
	}
	
	public float calcTargetX() {
		return (cachedPlayerX + player.getWidth() / 2f) + targetX;
	}
	
	public float calcTargetY() {
		return (cachedPlayerY + player.getHeight() / 2f) + targetY;
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
				activateFromSleep();
			}
		}
		else if(state == State.moving) {
			delayTimer += delta;
			
			if(delayTimer >= delay) {
				cachePlayerPosition();
				resetDelay();
			}
			
			if(calcTargetX() == getX() && calcTargetY() == getY()) {
				stopMovement();
			}
		}
		else {
			throw new RuntimeException("invalid minion state");
		}
	}
	
	public void activateFromSleep() {
		resetDelay();
		chooseTarget();
		startMovement();
	}
	
	private void chooseTarget() {
		cachePlayerPosition();
		
		final float radiusAngle = MathUtils.random(0f, 360f);
		final float radius = player.chooseMinionRadius();

		tmpVector.set(0f, radius);
		tmpVector.rotate(radiusAngle);
		
		targetX = tmpVector.x;
		targetY = tmpVector.y;
	}
	
	private void resetDelay() {
		delayTimer = 0f;
		delaySetup();
	}
	
	private void startMovement() {
		movementSpeedScaler = MathUtils.random(Config.MinionMovementSpeedRandomizationMin, Config.MinionMovementSpeedRandomizationMax);
		state = State.moving;
		startMovementAnimation();
	}
	
	private void stopMovement() {
		state = State.idle;
		endMovementAnimation();
	}
	
	private void delaySetup() {
		delay = MathUtils.random(Config.MinionReactionDelayMin, Config.MinionReactionDelayMax);
	}
	
}
