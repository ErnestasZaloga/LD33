package com.ld33.screens;

import com.ld33.App;
import com.ld33.utils.TouchListener;

public final class MenuScreen extends BaseScreen {

	public MenuScreen(final App app) {
		super(app);
		
		addListener(new TouchListener() {
			public void touched() {
				app.getScreens().changeToGameScreen();
			}
		});
	}
	
}
