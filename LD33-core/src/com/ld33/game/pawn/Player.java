package com.ld33.game.pawn;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;

public final class Player extends Pawn {

	private final App app;
	
	private int horizontalMovementState;
	private int verticalMovementState;
	
	private final Array<Minion> minions = new Array<Minion>();
	
	public Player(final App app) {
		this.app = app;
		setRegion(app.getAssets().mainCharacterRegion);
	}
	
	public float getMinionRadius() {
		return app.wpercent() * Config.PlayerRadiusPerMinion * minions.size;
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
		minion.begin();
	}
	
	public int getMinionCount() {
		return minions.size;
	}
	
	public Minion getMinion(final int index) {
		return minions.get(index);
	}
	
}
