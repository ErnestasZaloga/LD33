package com.ld33.game;

import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;

public class ProjectileManager {
	
	private App app;
	private Array<Projectile> projectiles;
	
	public ProjectileManager(App app) {
		this.app = app;
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
		app.getScreens().getGameScreen().getGameWorld().addActor(p);
	}
	
	public Array<Projectile> getProjectiles() {
		return projectiles;
	}
	
}
