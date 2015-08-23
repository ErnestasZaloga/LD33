package com.ld33;

import com.badlogic.gdx.Input.Keys;

public final class Config {

	public static final char WallTile = '#';
	public static final char GrassTile = '.';
	public static final char BoltTower = 'x';
	public static final char SpawnerTower = 's';
	public static final char PlayerStartPosition = '@';
	
	public static final float MeleeAttackRange = 25f;
	public static final float MeleeAttackInterval = 0.8f;
	public static final float RangedAttackRange = 150f;
	public static final float RangedAttackInterval = 1.9f;
	public static final float MagicalAttackRange = 90f;
	public static final float MagicalAttackInterval = 1.3f;

	public static final float PawnAnimationJumpDuration = 0.2f;
	public static final float PawnAnimationJumpHeight = 0.2f; // Relative to pawn's height
	
	public static final float PlayerMaxHealth = 100;
	public static final float PlayerTilesPerSecond = 2f;
	public static final float PlayerMinionRadius = 20f; // Relative to vertical percent of the screen
	public static final int PlayerInitialMinions = 5;
	public static final float PlayerSmallMinionRadiusScalerMin = 0.2f;
	public static final float PlayerSmallMinionRadiusScalerMax = 0.35f;
	public static final float PlayerMidMinionRadiusScalerMin = 0.5f;
	public static final float PlayerMidMinionRadiusScalerMax = 0.65f;
	public static final float PlayerLargeMinionRadiusScalerMin = 0.85f;
	public static final float PlayerLargeMinionRadiusScalerMax = 1f;

	public static final float MinionMaxHealth = 10;
	public static final float MinionReactionDelayMin = 0.5f;
	public static final float MinionReactionDelayMax = 1f;
	public static final float MinionTilesPerSecond = 2f;
	public static final float MinionMovementSpeedRandomizationMin = 0.9f;
	public static final float MinionMovementSpeedRandomizationMax = 1.1f;
	
	public static final float MinionVisionRange = 400f;
	public static final float EnemyMinionTilesPerSecond = 1.65f;
	
	public static final int MoveUpKey = Keys.W;
	public static final int MoveLeftKey = Keys.A;
	public static final int MoveDownKey = Keys.S;
	public static final int MoveRightKey = Keys.D;
	public static final int FormationLargeKey = Keys.NUM_3;
	public static final int FormationMidKey = Keys.NUM_2;
	public static final int FormationSmallKey = Keys.NUM_1;
	
	/* Bolt tower */
	public static final float BoltTowerHealth = 15f;
	public static final float BoltTowerRange = 300;
	public static final float BoltTowerDamage = 2;
	public static final float BoltTowerProjectileSpeed = 80f;
	public static final float BoltTowerAttackInterval = 1.5f;
	
	/* Spawner tower */
	public static final float SpawnerTowerHealth = 10f;
	public static final float SpawnerTowerRange = 250f;
	public static final float SpawnerTowerDamage = 1f;
	public static final float SpawnerTowerProjectileSpeed = 115f;
	public static final float SpawnerTowerAttackInterval = 1.2f;
	public static final float SpawnerTowerSpecialActionInterval = 2f;
	
}
