package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;

public class ProjectileManager implements ManagerInterface {
	
	private final App app;
	private final GameWorld gameWorld;
	
	private Array<Projectile> projectiles;
	
	public ProjectileManager(final App app,
							 final GameWorld gameWorld) {
		
		this.app = app;
		this.gameWorld = gameWorld;
		
		projectiles = new Array<Projectile>();
	}

	public void createBolt(float x, float y, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(x, y);
		p.setDamage(Config.BoltTowerDamage);
		p.setRange(Config.BoltTowerRange);
		p.setSpeed(Config.BoltTowerProjectileSpeed);
		float dx = targetX-x;
		float dy = targetY-y;
		p.setRotation((float)Math.atan2(dy, dx));
		projectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public Array<Projectile> getProjectiles() {
		return projectiles;
	}

	@Override
	public void update(final float delta) {
		for(final Projectile projectile : projectiles) {
			// TODO: logic
		}
	}
	
}
