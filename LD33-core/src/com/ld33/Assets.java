package com.ld33;

import com.workforfood.devkit.RAssets;
import com.workforfood.devkit.Resolution;
import com.workforfood.devkit.TextureRegionExt;

public final class Assets extends RAssets {

	public final float tileWidth;
	public final float tileHeight;
	
	public Assets(final Resolution resolution) {
		super(resolution);
		
		tileWidth = tileRegion.getWidth();
		tileHeight = tileRegion.getHeight();
	}
	
}
