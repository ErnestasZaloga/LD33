package ld33;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ld33.App;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LD33";
		
		cfg.width = 1024;
		cfg.height = 768;
		
		new LwjglApplication(new App(), cfg);
		
		final DisplayMode displayMode = Gdx.graphics.getDesktopDisplayMode();
		Gdx.graphics.setDisplayMode(displayMode.width, displayMode.height, true);
	}
}
