package com.ld33;

import com.badlogic.gdx.Input.Keys;

public final class Config {

	public static final char WallTile = '#';
	public static final char GrassTile = '.';
	public static final char BoltTower = 'x';
	public static final char PlayerStartPosition = '@';

	public static final float PlayerTps = 2f; // Player's tiles per second movement
	public static final float PlayerAnimationJumpDuration = 0.2f;
	public static final float PlayerAnimationJumpHeight = 0.2f; // Relative to player's height
	public static final float PlayerRadiusPerMinion = 0.1f; // Relative to vertical percent of the screen
	
	public static final float MinionReactionDelayMin = 0.2f;
	public static final float MinionReactionDelayMax = 0.5f;
	public static final float MinionPositionRadiusMin = 0.2f;
	
	public static final int MoveUpKey = Keys.W;
	public static final int MoveLeftKey = Keys.A;
	public static final int MoveDownKey = Keys.S;
	public static final int MoveRightKey = Keys.D;
	
	/* Bolt tower */
	public static final float BoltTowerHealth = 15f;
	public static final float BoltTowerRange = 300;
	public static final float BoltTowerDamage = 2;
	public static final float BoltTowerProjectileSpeed = 80f;
	public static final float BoltTowerAttackInterval = 1.5f;
	
}
