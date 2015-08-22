package com.ld33.game.environment;

import com.ld33.Config;
import com.ld33.game.GameWorld;
import com.workforfood.devkit.TextureRegionExt;

public class TileObject extends Tile {

	private final GameWorld gameWorld;
	
	private float health;
	private float range;
	private float damage;
	private float attackInterval;
	private float timeUntilAttack = 0;
	
	public TileObject(char type, TextureRegionExt textureRegionExt, GameWorld gameWorld) {
		super(type, textureRegionExt);
		this.gameWorld = gameWorld;
		setStats();
	}
	
	@Override
	public void act(float delta) {
		timeUntilAttack -= delta;
		if(timeUntilAttack < 0) timeUntilAttack = 0;
	}
	
	public void attack(float x, float y, float targetX, float targetY) {
		if(timeUntilAttack <= 0) {
			timeUntilAttack = attackInterval;
			System.out.println(gameWorld);
			gameWorld.getProjectileManager().createBolt(x, y, targetX, targetY);
		}
	}
	
	private void setStats() {
		if(type == Config.BoltTower) {
			this.health = Config.BoltTowerHealth;
			this.range = Config.BoltTowerRange;
			this.damage = Config.BoltTowerDamage;
			this.attackInterval = Config.BoltTowerAttackInterval;
		}
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getAttackInterval() {
		return attackInterval;
	}

	public void setAttackInterval(float attackInterval) {
		this.attackInterval = attackInterval;
	}

	public float getTimeUntilAttack() {
		return timeUntilAttack;
	}

	public void setTimeUntilAttack(float timeUntilAttack) {
		this.timeUntilAttack = timeUntilAttack;
	}

}
