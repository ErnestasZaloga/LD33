package com.ld33.game.pawn;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.utils.steps.FloatStep;
import com.ld33.utils.steps.Steps;

public final class Player extends Pawn {

	private final App app;
	
	private int horizontalMovementState;
	private int verticalMovementState;
	
	private float modY;
	private final FloatStep.Listener modYListener = new FloatStep.Listener() {
		
		@Override
		public void onChange(final FloatStep floatStep,
							 final float value) {
			
			modY = value;
		}
		
	};
	
	private final Array<Minion> minions = new Array<Minion>();
	
	public Player(final App app) {
		this.app = app;
		setRegion(app.getAssets().mainCharacterRegion);
	}
	
	public float getMinionRadius() {
		return app.wpercent() * Config.PlayerRadiusPerMinion * minions.size;
	}
	
	public float getPlaneY() {
		return getY();// - (getHeight() * Config.PlayerAnimationJumpHeight) * modY;
	}
	
	public float getJumpDisplacement() {
		return 0f;//getHeight() * Config.PlayerAnimationJumpHeight * modY;
	}
	
	public void startMoveLeft() {
		setMovementState(-1, verticalMovementState);
	}
	
	public void stopMoveLeft() {
		if(horizontalMovementState == -1) {
			setMovementState(0, verticalMovementState);
		}
	}
	
	public void startMoveRight() {
		setMovementState(1, verticalMovementState);
	}
	
	public void stopMoveRight() {
		if(horizontalMovementState == 1) {
			setMovementState(0, verticalMovementState);
		}
	}
	
	public void startMoveUp() {
		setMovementState(horizontalMovementState, 1);
	}
	
	public void stopMoveUp() {
		if(verticalMovementState == 1) {
			setMovementState(horizontalMovementState, 0);
		}
	}
	
	public void startMoveDown() {
		setMovementState(horizontalMovementState, -1);
	}
	
	public void stopMoveDown() {
		if(verticalMovementState == -1) {
			setMovementState(horizontalMovementState, 0);
		}
	}
	
	private void setMovementState(final int horizontal,
								  final int vertical) {
		
		final boolean wasMoving = isMoving();
		
		this.horizontalMovementState = horizontal;
		this.verticalMovementState = vertical;
		
		final boolean isMoving = isMoving();
		
		if(wasMoving && !isMoving) {
			//clearActions();
			//addAction(Steps.action(Steps._float(modY, 0, modY * (Config.PlayerAnimationJumpDuration / 2f), modYListener)));
		}
		else if(!wasMoving && isMoving) {
			//addAction(Steps.action(Steps.repeat(Steps.sequence(
			//			Steps._float(0f, 1f, (Config.PlayerAnimationJumpDuration / 2f), modYListener),
			//			Steps._float(1f, 0f, (Config.PlayerAnimationJumpDuration / 2f), modYListener)))));
		}
		
	}
	
	public boolean isMoving() {
		return horizontalMovementState != 0 || verticalMovementState != 0;
	}
	
	protected int getHorizontalMovementState() {
		return horizontalMovementState;
	}
	
	protected int getVerticalMovementState() {
		return verticalMovementState;
	}
	
	protected void registerMinion(final Minion minion) {
		minions.add(minion);
	}
	
	public int getMinionCount() {
		return minions.size;
	}
	
	public Minion getMinion(final int index) {
		return minions.get(index);
	}
	
	@Override
	public void act(final float delta) {
		final float jumpHeight = getHeight() * Config.PlayerAnimationJumpHeight;
		
		// Important! process before calling act
		final float realY = getY() - jumpHeight * modY;
		
		super.act(delta);
		
		//setY(realY + jumpHeight * modY);
	}
	
}
