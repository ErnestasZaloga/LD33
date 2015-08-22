package com.workforfood.devkit;

import com.esotericsoftware.spine.Animation;

public abstract class RAssets extends BaseAssets {


	// *************************
	// TEXTURES
	// *************************

	protected final AtlasResource BlockAtlas;

	public final TextureRegionExt blockRegion;

	protected final AtlasResource GameAtlas;

	public final TextureRegionExt grassTileRegion;
	public final TextureRegionExt largefontRegion;
	public final TextureRegionExt mainCharacterRegion;
	public final TextureRegionExt minionRegion;
	public final TextureRegionExt sandTileRegion;
	public final TextureRegionExt towerRegion;
	public final TextureRegionExt wallRegion;


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

		grassTileRegion = GameAtlas.find("grassTile");
		largefontRegion = GameAtlas.find("largefont");
		mainCharacterRegion = GameAtlas.find("mainCharacter");
		minionRegion = GameAtlas.find("minion");
		sandTileRegion = GameAtlas.find("sandTile");
		towerRegion = GameAtlas.find("tower");
		wallRegion = GameAtlas.find("wall");


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