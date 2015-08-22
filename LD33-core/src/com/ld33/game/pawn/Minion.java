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
		
		this.app = app;
		this.player = player;
	}
	
	public void begin() {
		
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
			}
		}
	}
	
	private void chooseTarget() {
		final float radiusOffset = MathUtils.random(Config.MinionPositionRadiusMin, 1f);
		final float radiusAngle = MathUtils.random(0f, 360f);
		
		final float centerPlayerX = player.getX() + player.getWidth() / 2f;
		final float centerPlayerY = player.getY() + player.getHeight() / 2f;
		final float radius = player.getMinionRadius();

		tmpVector.set(0f, radius * radiusOffset);
		tmpVector.rotate(radiusAngle);
		
		targetX = centerPlayerX + tmpVector.x;
		targetY = centerPlayerY + tmpVector.y;
	}
	
	private void delaySetup() {
		delay = MathUtils.random(Config.MinionReactionDelayMin, Config.MinionReactionDelayMax);
	}
	
}
