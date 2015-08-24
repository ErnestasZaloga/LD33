package com.workforfood.devkit;

import com.esotericsoftware.spine.Animation;

public abstract class RAssets extends BaseAssets {


	// *************************
	// TEXTURES
	// *************************

	protected final AtlasResource BlockAtlas;

	public final TextureRegionExt blockRegion;

	protected final AtlasResource GameAtlas;

	public final TextureRegionExt boltTowerRegion;
	public final TextureRegionExt enemyMeleeMinionBackRegion;
	public final TextureRegionExt enemyMeleeMinionBackLeftRegion;
	public final TextureRegionExt enemyMeleeMinionFrontRegion;
	public final TextureRegionExt enemyMeleeMinionFrontRightRegion;
	public final TextureRegionExt enemyMeleeMinionRightRegion;
	public final TextureRegionExt enemyRangedMinionBackRegion;
	public final TextureRegionExt enemyRangedMinionBackLeftRegion;
	public final TextureRegionExt enemyRangedMinionFrontRegion;
	public final TextureRegionExt enemyRangedMinionFrontRightRegion;
	public final TextureRegionExt enemyRangedMinionRightRegion;
	public final TextureRegionExt grassTile1Region;
	public final TextureRegionExt grassTile2Region;
	public final TextureRegionExt houseRegion;
	public final TextureRegionExt largefontRegion;
	public final TextureRegionExt minionShadowRegion;
	public final TextureRegionExt minionShadowIsoRegion;
	public final TextureRegionExt playerBackRegion;
	public final TextureRegionExt playerBackLeftRegion;
	public final TextureRegionExt playerFrontRegion;
	public final TextureRegionExt playerFrontRightRegion;
	public final TextureRegionExt playerMeleeMinionBackRegion;
	public final TextureRegionExt playerMeleeMinionBackLeftRegion;
	public final TextureRegionExt playerMeleeMinionFrontRegion;
	public final TextureRegionExt playerMeleeMinionFrontRightRegion;
	public final TextureRegionExt playerMeleeMinionRightRegion;
	public final TextureRegionExt playerRangedMinionBackRegion;
	public final TextureRegionExt playerRangedMinionBackLeftRegion;
	public final TextureRegionExt playerRangedMinionFrontRegion;
	public final TextureRegionExt playerRangedMinionFrontRightRegion;
	public final TextureRegionExt playerRangedMinionRightRegion;
	public final TextureRegionExt playerRightRegion;
	public final TextureRegionExt playerShadowRegion;
	public final TextureRegionExt playerShadowIsoRegion;
	public final TextureRegionExt projectileRegion;
	public final TextureRegionExt sandTileRegion;
	public final TextureRegionExt spawnTowerRegion;
	public final TextureRegionExt tileRegion;
	public final TextureRegionExt wall1Region;
	public final TextureRegionExt wall2Region;


	// *************************
	// FONTS
	// *************************


	// *************************
	// SOUNDS
	// *************************


	// *************************
	// STREAMS
	// *************************


	// *************************
	// SKELETONS
	// *************************


	// *************************
	// EFFECTS
	// *************************


	// *************************
	// SHADERS
	// *************************


	public RAssets(final Resolution resolution) {
		super(resolution);


		// *************************
		// TEXTURES
		// *************************

		BlockAtlas = loadAtlas("Block");

		blockRegion = BlockAtlas.find("block");

		GameAtlas = loadAtlas("Game");

		boltTowerRegion = GameAtlas.find("boltTower");
		enemyMeleeMinionBackRegion = GameAtlas.find("enemyMeleeMinionBack");
		enemyMeleeMinionBackLeftRegion = GameAtlas.find("enemyMeleeMinionBackLeft");
		enemyMeleeMinionFrontRegion = GameAtlas.find("enemyMeleeMinionFront");
		enemyMeleeMinionFrontRightRegion = GameAtlas.find("enemyMeleeMinionFrontRight");
		enemyMeleeMinionRightRegion = GameAtlas.find("enemyMeleeMinionRight");
		enemyRangedMinionBackRegion = GameAtlas.find("enemyRangedMinionBack");
		enemyRangedMinionBackLeftRegion = GameAtlas.find("enemyRangedMinionBackLeft");
		enemyRangedMinionFrontRegion = GameAtlas.find("enemyRangedMinionFront");
		enemyRangedMinionFrontRightRegion = GameAtlas.find("enemyRangedMinionFrontRight");
		enemyRangedMinionRightRegion = GameAtlas.find("enemyRangedMinionRight");
		grassTile1Region = GameAtlas.find("grassTile1");
		grassTile2Region = GameAtlas.find("grassTile2");
		houseRegion = GameAtlas.find("house");
		largefontRegion = GameAtlas.find("largefont");
		minionShadowRegion = GameAtlas.find("minionShadow");
		minionShadowIsoRegion = GameAtlas.find("minionShadowIso");
		playerBackRegion = GameAtlas.find("playerBack");
		playerBackLeftRegion = GameAtlas.find("playerBackLeft");
		playerFrontRegion = GameAtlas.find("playerFront");
		playerFrontRightRegion = GameAtlas.find("playerFrontRight");
		playerMeleeMinionBackRegion = GameAtlas.find("playerMeleeMinionBack");
		playerMeleeMinionBackLeftRegion = GameAtlas.find("playerMeleeMinionBackLeft");
		playerMeleeMinionFrontRegion = GameAtlas.find("playerMeleeMinionFront");
		playerMeleeMinionFrontRightRegion = GameAtlas.find("playerMeleeMinionFrontRight");
		playerMeleeMinionRightRegion = GameAtlas.find("playerMeleeMinionRight");
		playerRangedMinionBackRegion = GameAtlas.find("playerRangedMinionBack");
		playerRangedMinionBackLeftRegion = GameAtlas.find("playerRangedMinionBackLeft");
		playerRangedMinionFrontRegion = GameAtlas.find("playerRangedMinionFront");
		playerRangedMinionFrontRightRegion = GameAtlas.find("playerRangedMinionFrontRight");
		playerRangedMinionRightRegion = GameAtlas.find("playerRangedMinionRight");
		playerRightRegion = GameAtlas.find("playerRight");
		playerShadowRegion = GameAtlas.find("playerShadow");
		playerShadowIsoRegion = GameAtlas.find("playerShadowIso");
		projectileRegion = GameAtlas.find("projectile");
		sandTileRegion = GameAtlas.find("sandTile");
		spawnTowerRegion = GameAtlas.find("spawnTower");
		tileRegion = GameAtlas.find("tile");
		wall1Region = GameAtlas.find("wall1");
		wall2Region = GameAtlas.find("wall2");


		// *************************
		// FONTS
		// *************************


		// *************************
		// SOUNDS
		// *************************


		// *************************
		// STREAMS
		// *************************


		// *************************
		// SKELETONS
		// *************************


		// *************************
		// EFFECTS
		// *************************


		// *************************
		// SHADERS
		// *************************

	}
}