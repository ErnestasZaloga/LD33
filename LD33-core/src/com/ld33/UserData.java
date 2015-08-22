package com.ld33;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public final class UserData {

	private final Preferences prefs = Gdx.app.getPreferences("pre");
	private boolean soundsEnabled;
	
	public UserData() {
		soundsEnabled = prefs.getBoolean("sounds", true);
	}
	
	public void setSoundsEnabled(final boolean soundsEnabled) {
		this.soundsEnabled = soundsEnabled;
	}
	
	public boolean toggleSoundsEnabled() {
		this.soundsEnabled = !soundsEnabled;
		return soundsEnabled;
	}
	
	public boolean isSoundsEnabled() {
		return soundsEnabled;
	}
	
	public void save() {
		prefs.putBoolean("sounds", soundsEnabled);
	}
	
}