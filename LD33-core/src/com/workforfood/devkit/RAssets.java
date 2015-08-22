package com.workforfood.devkit;

import com.esotericsoftware.spine.Animation;

public abstract class RAssets extends BaseAssets {


	// *************************
	// TEXTURES
	// *************************

	protected final AtlasResource BlockAtlas;

	public final TextureRegionExt blockRegion;

	protected final AtlasResource GameAtlas;

	public final TextureRegionExt GrassRegion;
	public final TextureRegionExt SandRegion;
	public final TextureRegionExt TowerRegion;
	public final TextureRegionExt WallRegion;
	public final TextureRegionExt largefontRegion;


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

		GrassRegion = GameAtlas.find("Grass");
		SandRegion = GameAtlas.find("Sand");
		TowerRegion = GameAtlas.find("Tower");
		WallRegion = GameAtlas.find("Wall");
		largefontRegion = GameAtlas.find("largefont");


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