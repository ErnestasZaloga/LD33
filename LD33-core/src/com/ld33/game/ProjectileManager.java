package com.ld33.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.Projectile.DamageType;
import com.ld33.game.pawn.Pawn;

public class ProjectileManager implements ManagerInterface {
	
	private final App app;
	private final GameWorld gameWorld;
	
	private Array<Projectile> projectiles;
	private Array<Projectile> friendlyProjectiles;
	
	public ProjectileManager(final App app,
							 final GameWorld gameWorld) {
		
		this.app = app;
		this.gameWorld = gameWorld;
		
		projectiles = new Array<Projectile>();
		friendlyProjectiles = new Array<Projectile>();
	}

	public void createBolt(float x, float y, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(x, y);
		p.setDamage(Config.BoltTowerDamage);
		p.setRange(Config.BoltTowerRange);
		p.setSpeed(Config.BoltTowerProjectileSpeed);
		p.setDamageType(DamageType.PHYSICAL);
		float dx = targetX-x;
		float dy = targetY-y;
		p.setRotation((float)(Math.atan2(dy, dx) * 180/Math.PI));
		projectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public void createIceBolt(float x, float y, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(x, y);
		p.setDamage(Config.ElementalTowerDamage);
		p.setRange(Config.ElementalTowerRange);
		p.setSpeed(Config.ElementalTowerProjectileSpeed);
		p.setDamageType(DamageType.ICE);
		float dx = targetX-x;
		float dy = targetY-y;
		p.setRotation((float)(Math.atan2(dy, dx) * 180/Math.PI));
		projectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public void createFireBolt(float x, float y, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(x, y);
		p.setDamage(Config.ElementalTowerDamage);
		p.setRange(Config.ElementalTowerRange);
		p.setSpeed(Config.ElementalTowerProjectileSpeed);
		p.setDamageType(DamageType.FIRE);
		float dx = targetX-x;
		float dy = targetY-y;
		p.setRotation((float)(Math.atan2(dy, dx) * 180/Math.PI));
		projectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public void createFriendlyAttack(Pawn attacker, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(attacker.getX(), attacker.getPlaneY());
		p.setDamage(attacker.getDamage());
		p.setRange(attacker.getAttackRange());
		p.setSpeed(attacker.getProjectileSpeed());
		float dx = targetX-attacker.getX();
		float dy = targetY-attacker.getPlaneY();
		p.setRotation((float)(Math.atan2(dy, dx) * 180/Math.PI));
		friendlyProjectiles.add(p);
		
		gameWorld.contentGroup.addActor(p);
	}
	
	public void createUnfriendlyAttack(Pawn attacker, float targetX, float targetY) {
		Projectile p = new Projectile(app.getAssets().projectileRegion);
		p.setPosition(attacker.getX(), attacker.getPlaneY());
		p.setDamage(attacker.getDamage());
		p.setRange(attacker.getAttackRange());
		p.setSpeed(attacker.getProjectileSpeed());
		float dx = targetX-attacker.getX();
		float dy = targetY-attacker.getPlaneY();
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
			float dx = MathUtils.cosDeg(projectile.getRotation()) * delta * projectile.getSpeed();
			float dy = MathUtils.sinDeg(projectile.getRotation()) * delta * projectile.getSpeed();
			projectile.moveBy(dx, dy);
			//Apply scaling
			float targetScale = 0.8f;
			float percentageTravelled = projectile.getDistanceTraveled()/projectile.getRange();
			float resultingScale = 1 + (targetScale - 1) * percentageTravelled;
			projectile.setScale(resultingScale);
			//Check for collisions with player
			if(player.getX() <= projectile.getX()+projectile.getWidth() && projectile.getX() <= player.getX()+player.getWidth()) {  //X axis
				if(player.getPlaneY() <= projectile.getY()+projectile.getHeight() && projectile.getY() <= player.getPlaneY()+player.getHeight()) {  //Y axis
					player.damagePawn(projectile.getDamage());
					//Apply special effects
					//...
					
					projectiles.removeValue(projectile, true);
					projectile.remove();
				}
			}
			//Check for collisions with pawns TODO
			//Check if max range is reached
			projectile.increaseDistanceTraveled((float)Math.sqrt((float)(dx*dx+dy*dy)));
			if(projectile.getDistanceTraveled() >= projectile.getRange()) {
				//Apply special effects
				//...TODO
				
				projectiles.removeValue(projectile, true);
				projectile.remove();
			}
		}
		
		//Friendly projectiles
		for(final Projectile projectile : friendlyProjectiles) {
			//Move
			float dx = MathUtils.cosDeg(projectile.getRotation()) * delta * projectile.getSpeed();
			float dy = MathUtils.sinDeg(projectile.getRotation()) * delta * projectile.getSpeed();
			projectile.moveBy(dx, dy);
			//Apply scaling
			float targetScale = 0.8f;
			float percentageTravelled = projectile.getDistanceTraveled()/projectile.getRange();
			float resultingScale = 1 + (targetScale - 1) * percentageTravelled;
			projectile.setScale(resultingScale);
			//Check for collisions with towers
			
			//Check for collisions with pawns TODO
			//Check if max range is reached
			projectile.increaseDistanceTraveled((float)Math.sqrt((float)(dx*dx+dy*dy)));
			if(projectile.getDistanceTraveled() >= projectile.getRange()) {
				projectiles.removeValue(projectile, true);
				projectile.remove();
			}
		}
	}
	
}
