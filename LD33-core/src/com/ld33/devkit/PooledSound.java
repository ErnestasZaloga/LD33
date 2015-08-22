package com.ld33.devkit;

public final class PooledSound {
	
	private final SoundResource resource;
	
	private long id;
	private boolean askedForRelease;
	
	private boolean paused;
	private float volume = 1f;
	private float pitch = 1f;
	private float panning;

	protected PooledSound(final SoundResource resource) {
		this.resource = resource;
	}
	
	protected void play(final long id) {
		this.id = id;
	}
	
	public PooledSound resume() {
		if(paused) {
			paused = false;
			resource.getSound().resume(id);
		}
		return this;
	}
	
	public PooledSound pause() {
		if(!paused) {
			paused = true;
			resource.getSound().pause(id);
		}
		return this;
	}
	
	public boolean isPlaying() {
		return resource.isLoop() && !paused;
	}
	
	public PooledSound modifyPitch(final float pitch) {
		this.pitch = pitch;
		resource.getSound().setPitch(id, pitch);
		return this;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public PooledSound modifyVolume(final float volume) {
		this.volume = volume;
		updateVolume();
		return this;
	}
	
	public float getVolume() {
		return volume;
	}
	
	public PooledSound modifyPanning(final float panning) {
		this.panning = panning;
		resource.getSound().setPan(id, panning, calculateVolume());
		return this;
	}
	
	public float getPanning() {
		return panning;
	}
	
	public PooledSound release() {
		askedForRelease = true;
		return this;
	}
	
	protected void updateVolume() {
		resource.getSound().setVolume(id, calculateVolume());
	}
	
	private float calculateVolume() {
		return resource.getAudioStream().applyVolume(volume * resource.getVolume());
	}
	
	protected boolean shouldBeReleased() {
		return askedForRelease;
	}
	
	protected void reset() {
		id = 0;
		askedForRelease = false;
		paused = false;
		volume = 1f;
		pitch = 1f;
		panning = 0f;
	}
}