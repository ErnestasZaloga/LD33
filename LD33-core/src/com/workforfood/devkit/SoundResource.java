package com.workforfood.devkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public final class SoundResource implements Resource {
	
	private final AudioStream audioStream;
	private final Sound sound;
	private final float volume;
	private final boolean loop;
	
	private final Pool<PooledSound> soundInstancePool = new Pool<PooledSound>() {
		@Override
		protected PooledSound newObject() {
			return new PooledSound(SoundResource.this);
		}
	};
	private final Array<PooledSound> activeInstances = new Array<PooledSound>();
	
	public SoundResource(final AudioStream audioStream,
						 final String fileName) {
		
		this(audioStream, fileName, 1f, false);
	}
	
	public SoundResource(final AudioStream audioStream,
						 final String fileName,
						 final float volume) {
		
		this(audioStream, fileName, volume, false);
	}
	
	public SoundResource(final AudioStream audioStream,
						 final String fileName,
						 final boolean loop) {
		
		this(audioStream, fileName, 1f, loop);
	}
	
	public SoundResource(final AudioStream audioStream,
						 final String fileName,
						 final float volume,
						 final boolean loop) {
		
		if(audioStream == null) {
			throw new IllegalArgumentException("audioStream cannot be null");
		}
		if(fileName == null) {
			throw new IllegalArgumentException("fileName cannot be null");
		}
		if(volume < 0f || volume > 1f) {
			throw new IllegalArgumentException("volume must be between 0 and 1. volume: " + volume);
		}
		
		this.audioStream = audioStream;
		this.sound = Gdx.audio.newSound(Gdx.files.internal("sounds/" + fileName + ".mp3"));
		this.volume = volume;
		this.loop = loop;
	}
	
	public AudioStream getAudioStream() {
		return audioStream;
	}
	
	protected Sound getSound() {
		return sound;
	}
	
	protected float getVolume() {
		return volume;
	}
	
	public boolean isLoop() {
		return loop;
	}
	
	public PooledSound play() {
		for(int i = 0; i < activeInstances.size; i += 1) {
			final PooledSound activeInstance = activeInstances.get(i);

			if(activeInstance.shouldBeReleased()) {
				activeInstance.reset();
				activeInstances.removeIndex(i);
				i -= 1;
				
				audioStream.deactivate(activeInstance);
			}
		}
		
		final PooledSound instance = soundInstancePool.obtain();
		
		activeInstances.add(instance);
		audioStream.activate(instance);
		
		final long id;
		
		if(loop) {
			id = sound.loop(audioStream.applyVolume(volume));
		}
		else {
			id = sound.play(audioStream.applyVolume(volume));
		}
		
		instance.play(id);
		return instance;
	}
	
	public PooledSound playTemp() {
		return play().release();
	}
	
	@Override
	public void dispose() {
		sound.dispose();
	}
	
}
