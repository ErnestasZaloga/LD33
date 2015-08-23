package com.ld33.game.pawn;

import com.ld33.App;
import com.ld33.Config;

public class EnemyMinion extends Pawn {

	private App app;
	private Player player;
	private float direction = 0;
//	private float visionRange = Config.MinionVisionRange;
	private boolean active = false;
	
	public EnemyMinion(App app, Player player) {
		super(Config.MinionMaxHealth);
		
		this.app = app;
		this.player = player;
		
		setRegion(app.getAssets().minionRegion);
	}
	
	@Override
	public void act(final float delta) {
		super.act(delta);
		if(playerInRange()) {
			setActive(true);
		} else {
			setActive(false);
		}
		calculateDirection();
	}
	
	private boolean playerInRange() {
		float dx = player.getX()-this.getX();
		float dy = player.getPlaneY()-this.getPlaneY();
		float distance = (float)Math.sqrt(dx*dx+dy*dy);
		if(distance <= visionRange) {
			return true;
		}
		return false;
	}
	
	private void calculateDirection() {
		float dx = player.getX()-this.getX();
		float dy = player.getPlaneY()-this.getPlaneY();
		direction = (float)(Math.atan2(dy, dx) * 180/Math.PI);
	}

	public float getDirection() {
		return direction;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		if(active && !this.active) {
			startMovementAnimation();
		} else if(!active && this.active) {
			endMovementAnimation();
		}
		this.active = active;
	}

}
