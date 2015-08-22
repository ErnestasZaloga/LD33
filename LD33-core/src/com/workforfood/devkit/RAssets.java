package com.workforfood.devkit;

import com.esotericsoftware.spine.Animation;

public abstract class RAssets extends BaseAssets {


	// *************************
	// TEXTURES
	// *************************

	protected final AtlasResource BlockAtlas;

	public final TextureRegionExt blockRegion;

	protected final AtlasResource GameAtlas;

	public final TextureRegionExt boxRegion;
	public final TextureRegionExt wallRegion;
	public final TextureRegionExt grassTileRegion;
	public final TextureRegionExt largefontRegion;
	public final TextureRegionExt mainCharacterRegion;
	public final TextureRegionExt minionRegion;
	public final TextureRegionExt projectileRegion;
	public final TextureRegionExt sandTileRegion;
	public final TextureRegionExt tileRegion;
	public final TextureRegionExt towerRegion;


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

		boxRegion = GameAtlas.find("box");
		wallRegion = GameAtlas.find("wall");
		grassTileRegion = GameAtlas.find("grassTile");
		largefontRegion = GameAtlas.find("largefont");
		mainCharacterRegion = GameAtlas.find("mainCharacter");
		minionRegion = GameAtlas.find("minion");
		projectileRegion = GameAtlas.find("projectile");
		sandTileRegion = GameAtlas.find("sandTile");
		tileRegion = GameAtlas.find("tile");
		towerRegion = GameAtlas.find("tower");


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