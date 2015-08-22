package com.ld33.game;

import com.ld33.utils.SpriteActor;
import com.workforfood.devkit.TextureRegionExt;

public class Projectile extends SpriteActor {
	
	private float damage;
	private float range;
	private float speed;
	private boolean dead = false;
	
	public Projectile(TextureRegionExt region) {
		this.setRegion(region);
	}

	@Override
	public void act(float delta) {
		//TODO
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

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
}
