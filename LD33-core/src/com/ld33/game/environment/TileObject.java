package com.ld33.game.environment;

import com.ld33.Config;

public class TileObject extends Tile {

	private float health;
	private float range;
	private float damage;
	private float attackInterval;
	private float timeUntilAttack = 0;
	private float specialActionInterval;
	private float timeUntilSpecialAction = 0;
	private boolean hasSpecialAction = false;
	
	public TileObject(final char type,
					  final int xIndex,
					  final int yIndex) {
		
		super(type, xIndex, yIndex, true);
		setStats();
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		timeUntilAttack -= delta;
		if(timeUntilAttack < 0) timeUntilAttack = 0;
		
		timeUntilSpecialAction -= delta;
		if(timeUntilSpecialAction < 0) timeUntilSpecialAction = 0;
	}
	
	private void setStats() {
		if(type == Config.BoltTower) {
			this.health = Config.BoltTowerHealth;
			this.range = Config.BoltTowerRange;
			this.damage = Config.BoltTowerDamage;
			this.attackInterval = Config.BoltTowerAttackInterval;
		}
		else if(type == Config.SpawnerTower) {
			this.health = Config.SpawnerTowerHealth;
			this.range = Config.SpawnerTowerRange;
			this.damage = Config.SpawnerTowerDamage;
			this.attackInterval = Config.SpawnerTowerAttackInterval;
			this.specialActionInterval = Config.SpawnerTowerSpecialActionInterval;
			this.hasSpecialAction = true;
		}
		else if(type == Config.ElementalTower) {
			this.health = Config.ElementalTowerHealth;
			this.range = Config.ElementalTowerRange;
			this.damage = Config.ElementalTowerDamage;
			this.attackInterval = Config.ElementalTowerAttackInterval;
			this.specialActionInterval = -1f;
			this.hasSpecialAction = false;
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
	
	public boolean canAttack() {
		return timeUntilAttack <= 0;
	}
	
	public boolean canPerformSpecialAction() {
		return (timeUntilSpecialAction <= 0 && hasSpecialAction);
	}
	
	public void startCooldown() {
		timeUntilAttack = attackInterval;
	}
	
	public void startSpecialActionCooldown() {
		timeUntilSpecialAction = specialActionInterval;
	}

}
