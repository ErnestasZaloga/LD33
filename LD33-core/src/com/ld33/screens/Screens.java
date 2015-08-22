package com.ld33.screens;

import com.ld33.App;

public final class Screens {

	private final App app;
	
	private final MenuScreen menuScreen;
	private final GameScreen gameScreen;
	
	private BaseScreen currentScreen;
	private boolean initialized;
	
	public Screens(final App app) {
		this.app = app;
		
		menuScreen = new MenuScreen(app);
		gameScreen = new GameScreen(app);
		
		app.getStage().addActor(menuScreen);
		currentScreen = menuScreen;
	}
	
	public void setSize(final float width,
						final float height) {
		
		menuScreen.setSize(width, height);
		gameScreen.setSize(width, height);
		
		if(!initialized) {
			initialized = true;
			menuScreen.onShow();
		}
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public void changeToMenuScreen() {
		change(menuScreen);
	}
	
	public void changeToGameScreen() {
		change(gameScreen);
	}
	
	public void change(final BaseScreen newScreen) {
		currentScreen.onHide();
		currentScreen.remove();
		
		currentScreen = newScreen;
		app.getStage().addActor(currentScreen);
		currentScreen.onShow();
	}
	
	public BaseScreen getCurrentScreen() {
		return currentScreen;
	}
	
}
