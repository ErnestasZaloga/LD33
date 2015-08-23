package com.ld33.game.pawn;

import com.badlogic.gdx.math.MathUtils;
import com.ld33.App;
import com.ld33.Config;

public class EnemyMinion extends Pawn {

	private App app;
	private Player player;
	private float direction = 0;
	private float range = Config.EnemyMinionVisionRange;
	
	public EnemyMinion(App app, Player player) {
		super(Config.MinionMaxHealth);
		
		this.app = app;
		this.player = player;
		
		setRegion(app.getAssets().minionRegion);
	}
	
	@Override
	public void act(final float delta) {
		if(!playerInRange()) return;
		
		setDirection();
		this.moveBy(Config.EnemyMinionTilesPerSecond*delta*MathUtils.cosDeg(direction), Config.EnemyMinionTilesPerSecond*delta*MathUtils.sinDeg(direction));
	}
	
	private boolean playerInRange() {
		float dx = player.getX()-this.getX();
		float dy = player.getY()-this.getY();
		float distance = (float)Math.sqrt(dx*dx+dy*dy);
		if(distance <= range) {
			return true;
		}
		return false;
	}
	
	private void setDirection() {
		float dx = player.getX()-this.getX();
		float dy = player.getY()-this.getY();
		direction = (float)(Math.atan2(dy, dx) * 180/Math.PI);
	}

}
