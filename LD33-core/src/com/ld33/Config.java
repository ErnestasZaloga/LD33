package com.ld33;

import com.badlogic.gdx.Input.Keys;

public final class Config {


	public static final char WallTile = '#';
	public static final char GrassTile = '.';
	public static final char BoltTower = 'x';

	public static final float PLAYER_TPS = 2f; // Player's tiles per second movement
	public static final float PLAYER_ANIM_JUMP_DURATION = 0.2f;
	public static final float PLAYER_ANIM_JUMP_HEIGHT = 0.2f; // Relative to player's height
	
	public static final int MOVE_UP_KEY = Keys.W;
	public static final int MOVE_LEFT_KEY = Keys.A;
	public static final int MOVE_DOWN_KEY = Keys.S;
	public static final int MOVE_RIGHT_KEY = Keys.D;
	
	/* Bolt tower */
	public static final float BoltTowerHealth = 15f;
	public static final float BoltTowerRange = 300;
	public static final float BoltTowerDamage = 2;
	public static final float BoltTowerProjectileSpeed = 80f;
	public static final float BoltTowerAttackInterval = 1.5f;
	
}
