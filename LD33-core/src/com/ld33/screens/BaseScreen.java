package com.ld33.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.ld33.App;

abstract public class BaseScreen extends Group {

	protected final App app;
	
	public BaseScreen(final App app) {
		this.app = app;
		setTransform(false);
	}
	
	protected void onShow() {
	}
	
	protected void onHide() {
	}
}
