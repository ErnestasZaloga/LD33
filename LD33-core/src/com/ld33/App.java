package com.ld33;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.ld33.devkit.Resolution;
import com.ld33.screens.Screens;
import com.ld33.utils.DeltaTime;

public final class App implements ApplicationListener {

	private Stage stage;
	private PolygonSpriteBatch batch;
	private Assets assets;
	private AudioManager audio;
	private Resolution resolution;
	private UserData userData;
	private float wSpace;
	private float hSpace;
	private Screens screens;
	private boolean resumed;
	public final DeltaTime deltaTime = new DeltaTime(8);
	private boolean initialized;
	
	@Override
	public void create() {
		wSpace = Gdx.graphics.getWidth() * 0.01f;
		hSpace = Gdx.graphics.getHeight() * 0.01f;
		
		batch = new PolygonSpriteBatch(10000);
		
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		
		if(width < height) {
			final int tmp = width;
			width = height;
			height = tmp;
		}
		
		stage = new Stage(new StretchViewport(width, height), batch);
		
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		
		userData = new UserData();
		
		resolution = Resolution.resolve(
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(),
				Resolution.LIST);
		
		deltaTime.reset();
		
		assets = new Assets(resolution);
		audio = new AudioManager(this);
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		assets.audioStream.setEnabled(userData.isSoundsEnabled());
	}

	@Override
	public void dispose() {
		if(assets != null) {
			assets.dispose();
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		deltaTime.update();

		if(resumed) {
			deltaTime.reset();
			resumed = false;
		}
		
		stage.act(deltaTime.get());
		stage.draw();

		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void resize(int width, int height) {
		if(width < height) {
			final int tmp = width;
			width = height;
			height = tmp;
		}
		
		wSpace = Gdx.graphics.getWidth() * 0.01f;
		hSpace = Gdx.graphics.getHeight() * 0.01f;
		
		stage.getViewport().update(width, height, true);
		
		if(!initialized) {
			initialized = true;
			screens = new Screens(this);
		}

		if(screens != null) {
			screens.setSize(width, height);
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
		resumed = true;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public PolygonSpriteBatch getBatch() {
		return batch;
	}
	
	public Assets getAssets() {
		return assets;
	}
	
	public AudioManager getAudio() {
		return audio;
	}

	public Resolution getResolution() {
		return resolution;
	}
	
	public UserData getUserData() {
		return userData;
	}
	
	public float wpercent() {
		return wSpace;
	}
	
	public float hpercent() {
		return hSpace;
	}
	
	public Screens getScreens() {
		return screens;
	}
	
}
