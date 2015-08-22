package com.ld33.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.pawn.Pawn;

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
		p.setRotation((float)(Math.atan2(dy, dx) * 180/Math.PI));
		projectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public Array<Projectile> getProjectiles() {
		return projectiles;
	}

	@Override
	public void update(final float delta) {
		Pawn player = gameWorld.getPawnManager().getPlayer();
		for(final Projectile projectile : projectiles) {
			//Move
			float dx = MathUtils.cosDeg(projectile.getRotation());
			float dy = MathUtils.sinDeg(projectile.getRotation());
			projectile.moveBy(dx, dy);
			//Check for collisions with player
			if(player.getX() <= projectile.getX()+projectile.getWidth() && projectile.getX() <= player.getX()+player.getWidth()) {  //X axis
				if(player.getY() <= projectile.getY()+projectile.getHeight() && projectile.getY() <= player.getY()+player.getHeight()) {  //Y axis
					player.damagePawn(projectile.getDamage());
					projectiles.removeValue(projectile, true);
					projectile.remove();
				}
			}
			//Check for collisions with pawns TODO
			projectile.increaseDistanceTraveled((float)Math.sqrt((float)(dx*dx+dy*dy)));
			if(projectile.getDistanceTraveled() >= projectile.getRange()) {
				projectiles.removeValue(projectile, true);
				projectile.remove();
			}
		}
	}
	
}
