package com.ld33;

import com.workforfood.devkit.RAssets;
import com.workforfood.devkit.Resolution;

public final class Assets extends RAssets {

	public final float tileWidth;
	public final float tileHeight;
	public final float depthHeightScaling;
	
	public Assets(final Resolution resolution) {
		super(resolution);
		
		tileWidth = tileRegion.getWidth();
		tileHeight = tileRegion.getHeight();
		depthHeightScaling = tileHeight / boxRegion.getHeight();
	}
	
}
