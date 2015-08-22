package com.workforfood.devkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class StreamedAudioResource implements Resource {
	
	public static enum Category {
		MUSIC,
		SOUND;
	}
	
	public static interface OnCompletionListener {
		public void onCompletion(final StreamedAudioResource streamedAudioResource);
	}

	private final AudioStream audioStream;
	private final Category category;
	private final Music music;
	private final float volume;
	private final boolean loop;
	
	private float panning;
	private boolean paused;
	private float userVolume = 1f;
	
	private OnCompletionListener completionListener;
	private final Music.OnCompletionListener musicCompletionListener = new Music.OnCompletionListener() {
		@Override
		public void onCompletion(final Music music) {
			audioStream.deactivate(StreamedAudioResource.this);
			
			if(completionListener != null) {
				completionListener.onCompletion(StreamedAudioResource.this);
			}
			
			if(!isPlaying() && !paused) {
				resetUserState();
			}
		}
	};
	
	public StreamedAudioResource(final AudioStream audioStream,
								 final Category category,
						 		 final String fileName) {
		
		this(audioStream, category, fileName, 1f, true);
	}
	
	public StreamedAudioResource(final AudioStream audioStream,
								 final Category category,
						 		 final String fileName,
						 		 final float volume) {
		
		this(audioStream, category, fileName, volume, true);
	}
	public StreamedAudioResource(final AudioStream audioStream,
								 final Category category,
						 		 final String fileName,
						 		 final boolean loop) {
		
		this(audioStream, category, fileName, 1f, loop);
	}
	
	public StreamedAudioResource(final AudioStream audioStream,
								 final Category category,
						 		 final String fileName,
						 		 final float volume,
						 		 final boolean loop) {
		
		if(audioStream == null) {
			throw new IllegalArgumentException("audioStream cannot be null");
		}
		if(category == null) {
			throw new IllegalArgumentException("category cannot be null");
		}
		if(fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		if(volume < 0f || volume > 1f) {
			throw new IllegalArgumentException("volume must be between 0 and 1. volume: " + volume);
		}
		
		this.audioStream = audioStream;
		this.category = category;
		this.music = Gdx.audio.newMusic(Gdx.files.internal("streams/" + fileName + ".mp3"));
		this.volume = volume;
		this.loop = loop;
		
		music.setLooping(loop);
		music.setOnCompletionListener(musicCompletionListener);
	}
	
	public AudioStream getAudioStream() {
		return audioStream;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public boolean isLoop() {
		return loop;
	}
	
	public boolean isPlaying() {
		return music.isPlaying();
	}

	public StreamedAudioResource play() {
		return play(null);
	}
	
	public StreamedAudioResource play(final OnCompletionListener completionListener) {
		audioStream.deactivate(this);
		
		this.completionListener = completionListener;

		if(paused) {
			paused = false;
		}
		else {
			resetUserState();
		}
		
		audioStream.activate(this);
		
		return this;
	}
	
	public StreamedAudioResource stop() {
		paused = false;
		audioStream.deactivate(this);
		
		return this;
	}
	
	public StreamedAudioResource pause() {
		if(music.isPlaying()) {
			paused = true;
			music.pause();
		}
		
		return this;
	}
	
	public boolean isPaused() {
		return paused;
	}
	
	public void modifyPanning(final float panning) {
		if(panning < -1 || panning > 1) {
			throw new IllegalArgumentException("panning must be between -1 and 1. panning: " + panning);
		}
		
		if(canModify()) {
			this.panning = panning;
			music.setPan(panning, calculateVolume());
		}
	}
	
	public float getPanning() {
		return panning;
	}
	
	public void modifyVolume(final float volume) {
		if(canModify()) {
			this.userVolume = volume;
			updateVolume();
		}
	}
	
	public float getVolume() {
		return userVolume;
	}

	public void modifyPosition(final float position) {
		if(canModify()) {
			music.setPosition(position);
		}
	}
	
	public float getPosition() {
		return music.getPosition();
	}
	
	protected void playMusic() {
		music.play();
	}
	
	protected void stopMusic() {
		if(isPlaying()) {
			music.stop();
		}
	}
	
	protected void updateVolume() {
		music.setVolume(calculateVolume());
	}
	
	private float calculateVolume() {
		return audioStream.applyVolume(userVolume * volume);
	}
	
	private boolean canModify() {
		return isPlaying() || paused;
	}
	
	private void resetUserState() {
		userVolume = 1f;
		panning = 0f;
		
		music.setPosition(0f);
		music.setVolume(calculateVolume());
		music.setPan(panning, calculateVolume());
	}
	
	@Override
	public void dispose() {
		music.dispose();
	}
	
}
