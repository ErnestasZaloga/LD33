package com.ld33.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.StringBuilder;

public final class SecurePreferences {

	private final Preferences preferences;
	private final StringBuilder sb = new StringBuilder();
	
	public SecurePreferences(final String preferencesName) {
		this.preferences = Gdx.app.getPreferences(preferencesName);
	}
	
	public Preferences getPreferences() {
		return preferences;
	}
	
	public void putString(final String key, 
						  final String value) {
		
		preferences.putString(encryptKey(key), encryptString(value));
	}
	
	public String getString(final String key, 
							final String defaultValue) {
		
		return decryptString(preferences.getString(encryptKey(key), encryptString(defaultValue)));
	}
	
	public String getString(final String key) {
		return decryptString(preferences.getString(encryptKey(key)));
	}
	
	public void putInteger(final String key, 
					   	   final int value) {
		
		preferences.putString(encryptKey(key), encryptInteger(value));
	}
	
	public int getInteger(final String key, 
					  	  final int defaultValue) {
		
		return decryptInteger(preferences.getString(encryptKey(key), encryptInteger(defaultValue)));
	}
	
	public int getInteger(final String key) {
		return decryptInteger(preferences.getString(encryptKey(key)));
	}
	
	public void putFloat(final String key, 
						 final float value) {
		
		preferences.putString(encryptKey(key), encryptFloat(value));
	}
	
	public float getFloat(final String key, 
						  final float defaultValue) {
		
		return decryptFloat(preferences.getString(encryptKey(key), encryptFloat(defaultValue)));
	}
	
	public float getFloat(final String key) {
		return decryptFloat(preferences.getString(encryptKey(key)));
	}
	
	public void putBoolean(final String key, 
						   final boolean value) {
		
		preferences.putString(encryptKey(key), encryptBoolean(value));
	}
	
	public boolean getBoolean(final String key, 
							  final boolean defaultValue) {
		
		return decryptBoolean(preferences.getString(encryptKey(key), encryptBoolean(defaultValue)));
	}
	
	public boolean getBoolean(final String key) {
		return decryptBoolean(preferences.getString(encryptKey(key)));
	}
	
	public void clear() {
		preferences.clear();
	}
	
	public void flush() {
		preferences.flush();
	}

	private String encryptKey(final String key) {
		return encrypt(key);
	}
	
	private String encryptString(final String value) {
		if(value == null) {
			return null;
		}
		
		return encrypt(value);
	}
	
	private String decryptString(final String value) {
		if(value == null) {
			return null;
		}
		
		return decrypt(value);
	}
	
	private String encryptInteger(final int value) {
		return encrypt(String.valueOf(value));
	}
	
	private int decryptInteger(final String value) {
		return Integer.parseInt(decrypt(value));
	}
	
	private String encryptBoolean(final boolean value) {
		return encrypt(String.valueOf(value));
	}
	
	private boolean decryptBoolean(final String value) {
		return Boolean.parseBoolean(decrypt(value));
	}
	
	private String encryptFloat(final float value) {
		return encrypt(String.valueOf(value));
	}
	
	private float decryptFloat(final String value) {
		return Float.parseFloat(decrypt(value));
	}

	private String encrypt(final String value) {
		for(int i = 0, n = value.length(); i < n; ++i) {
			sb.append((char)(~value.charAt(i)));
		}
		
		final String retVal = sb.toString();
		sb.length = 0;
		
		return retVal;
	}
	
	private String decrypt(final String value) {
		return encrypt(value);
	}

}
