package com.ld33.game.pawn;

import com.ld33.utils.SpriteActor;

public abstract class Pawn extends SpriteActor {
	
	private float health;
	
	public void damagePawn(float damage) {
		this.health -= damage;
	}
	
}
