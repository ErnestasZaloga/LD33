package com.ld33.devkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;
import com.ld33.devkit.StreamedAudioResource.Category;

public final class AudioStream implements Resource {

	private final Sound noiseSound;
	private boolean enabled;
	private StreamedAudioResource activeMusic;
	private final Array<StreamedAudioResource> activeStreamedAudio = new Array<StreamedAudioResource>();
	private final Array<PooledSound> activeSoundInstances = new Array<PooledSound>();
	
	public AudioStream() {
		noiseSound = Gdx.audio.newSound(Gdx.files.internal("noise.mp3"));
		noiseSound.loop(1f);
	}

	protected void activate(final PooledSound soundInstance) {
		activeSoundInstances.add(soundInstance);
	}
	
	protected void deactivate(final PooledSound soundInstance) {
		activeSoundInstances.removeValue(soundInstance, true);
	}
	
	protected void activate(final StreamedAudioResource streamedAudio) {
		streamedAudio.playMusic();
		
		if(streamedAudio.getCategory() == Category.MUSIC) {
			if(activeMusic != null) {
				activeMusic.stopMusic();
			}
			activeMusic = streamedAudio;
		}
		else {
			if(!activeStreamedAudio.contains(streamedAudio, true)) {
				activeStreamedAudio.add(streamedAudio);
			}
		}
	}

	protected void deactivate(final StreamedAudioResource streamedAudio) {
		streamedAudio.stopMusic();
		
		if(streamedAudio.getCategory() == Category.MUSIC) {
			activeMusic = null;
		}
		else {
			activeStreamedAudio.removeValue(streamedAudio, true);
		}
	}

	protected float applyVolume(final float volume) {
		return (enabled ? 1f : 0f) * volume;
	}
	
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
		
		if(activeMusic != null) {
			activeMusic.updateVolume();
		}
		
		for(int i = 0; i < activeStreamedAudio.size; i += 1) {
			activeStreamedAudio.get(i).updateVolume();
		}
		
		for(int i = 0; i < activeSoundInstances.size; i += 1) {
			activeSoundInstances.get(i).updateVolume();
		}
	}
	
	public boolean toggleEnabled() {
		setEnabled(!enabled);
		return enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	@Override
	public void dispose() {
		noiseSound.stop();
		noiseSound.dispose();
	}
	
}
