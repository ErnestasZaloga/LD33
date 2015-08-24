package com.ld33.game.pawn;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;

public final class Player extends Pawn {

	private final App app;
	
	private int horizontalMovementState;
	private int verticalMovementState;
	
	private float minionRadiusScalerMin = Config.PlayerMidMinionRadiusScalerMin;
	private float minionRadiusScalerMax = Config.PlayerMidMinionRadiusScalerMax;
	private final Array<Minion> minions = new Array<Minion>();
	
	public Player(final App app) {
		super(Config.PlayerMaxHealth);
		
		this.app = app;
		setRegion(app.getAssets().playerFrontRegion);
	}
	
	public float chooseMinionRadius() {
		return app.wpercent() * Config.PlayerMinionRadius * MathUtils.random(minionRadiusScalerMin, minionRadiusScalerMax);
	}
	
	public void activateSmallMinionRadius() {
		setMinionRadiusScaler(Config.PlayerSmallMinionRadiusScalerMin, Config.PlayerSmallMinionRadiusScalerMax);
	}
	
	public void activateMidMinionRadius() {
		setMinionRadiusScaler(Config.PlayerMidMinionRadiusScalerMin, Config.PlayerMidMinionRadiusScalerMax);
	}
	
	public void activateLargeMinionRadius() {
		setMinionRadiusScaler(Config.PlayerLargeMinionRadiusScalerMin, Config.PlayerLargeMinionRadiusScalerMax);
	}
	
	private void setMinionRadiusScaler(final float minionRadiusScalerMin,
									   final float minionRadiusScalerMax) {
		
		if(this.minionRadiusScalerMin != minionRadiusScalerMin ||
		   this.minionRadiusScalerMax != minionRadiusScalerMax) {
			
			this.minionRadiusScalerMin = minionRadiusScalerMin;
			this.minionRadiusScalerMax = minionRadiusScalerMax;
			bumpMinions();
		}
	}
	
	private void bumpMinions() {
		for(int i = 0; i < minions.size; i += 1) {
			minions.get(i).activateFromSleep();
		}
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
			endMovementAnimation();
		}
		else if(!wasMoving && isMoving) {
			startMovementAnimation();
		}
		
		if(horizontal == 1 && vertical == 0) {
			setRegion(app.getAssets().playerRightRegion);
		}
		else if(horizontal == 1 && vertical == -1) {
			setRegion(app.getAssets().playerFrontRightRegion);
		}
		else if(horizontal == 1 && vertical == 1) {
			setRegion(app.getAssets().playerBackLeftRegion);
		}
		else if(horizontal == 0 && vertical == 1) {
			setRegion(app.getAssets().playerBackRegion);
		}
		else if(horizontal == 0 && vertical == -1) {
			setRegion(app.getAssets().playerFrontRegion);
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
	
}
