package com.ld33.game;

import com.ld33.utils.SpriteActor;
import com.workforfood.devkit.TextureRegionExt;

public class Projectile extends SpriteActor {
	
	public static enum DamageType {
		PHYSICAL, ICE, FIRE
	};
	
	private float damage;
	private float range;
	private float speed;
	private float distanceTraveled = 0;
	private DamageType damageType;
	
	public Projectile(TextureRegionExt region) {
		this.setRegion(region);
	}
	
	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getDistanceTraveled() {
		return distanceTraveled;
	}

	public void increaseDistanceTraveled(float delta) {
		this.distanceTraveled += delta;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
}
