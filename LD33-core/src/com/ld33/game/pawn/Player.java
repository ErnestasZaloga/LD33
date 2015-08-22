package com.ld33.game.pawn;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;

public final class Player extends Pawn {

	private final App app;
	
	private int horizontalMovementState;
	private int verticalMovementState;
	
	private final Array<Minion> minions = new Array<Minion>();
	
	public Player(final App app) {
		this.app = app;
		setRegion(app.getAssets().mainCharacterRegion);
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
		
		this.horizontalMovementState = horizontal;
		this.verticalMovementState = vertical;
	}
	
	protected int getHorizontalMovementState() {
		return horizontalMovementState;
	}
	
	protected int getVerticalMovementState() {
		return verticalMovementState;
	}
	
	public void registerMinion(final Minion minion) {
		minions.add(minion);
	}
	
}
