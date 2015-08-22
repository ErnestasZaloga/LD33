package com.ld33.devkit;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.ld33.devkit.StreamedAudioResource.Category;

public abstract class BaseAssets implements Disposable {

	public final Resolution resolution;
	public final AudioStream audioStream;
	
	private final Array<Resource> resources = new Array<Resource>();
	
	public BaseAssets(final Resolution resolution) {
		this.resolution = resolution;
		this.audioStream = new AudioStream();
	}
	
	protected AtlasResource loadAtlas(final String atlasName) {
		final AtlasResource atlas = new AtlasResource(resolution, atlasName);
		resources.add(atlas);
		return atlas;
	}
	
	protected ShaderResource loadShader(final String vertexFileName,
										final String fragmentFileName) {
		
		final ShaderResource resource = new ShaderResource(vertexFileName, fragmentFileName);
		resources.add(resource);
		return resource;
	}

	protected ParticleEffectResource loadParticleEffect(final String effectName,
														final AtlasResource atlas,
														final Resolution resolution) {
		
		final ParticleEffectResource resource = new ParticleEffectResource(effectName, atlas, resolution);
		resources.add(resource);
		return resource;
	}
	
	protected ParticleEffectResource loadParticleEffect(final String effectName,
														final AtlasResource atlas,
														final Resolution resolution,
														final float scale) {
		
		final ParticleEffectResource resource = new ParticleEffectResource(effectName, atlas, resolution, scale);
		resources.add(resource);
		return resource;
	}
	
	protected FontResource loadFont(final String fontName,
									final String regionName,
									final AtlasResource atlas) {
		
		final FontResource font = new FontResource(resolution, atlas, fontName, regionName);
		resources.add(font);
		return font;
	}
	
	protected SoundResource loadSound(final String soundName) {
		final SoundResource sound = new SoundResource(audioStream, soundName);
		resources.add(sound);
		return sound;
	}
	
	protected SoundResource loadSound(final String soundName,
									  final float volume) {
		
		final SoundResource sound = new SoundResource(audioStream, soundName, volume);
		resources.add(sound);
		return sound;
	}
	
	protected SoundResource loadSound(final String soundName,
									  final boolean loop) {
		
		final SoundResource sound = new SoundResource(audioStream, soundName, loop);
		resources.add(sound);
		return sound;
	}
	
	protected SoundResource loadSound(final String soundName,
									  final float volume,
									  final boolean loop) {
		
		final SoundResource sound = new SoundResource(audioStream, soundName, volume, loop);
		resources.add(sound);
		return sound;
	}
	
	protected StreamedAudioResource loadMusic(final String musicName) {
		final StreamedAudioResource music = new StreamedAudioResource(audioStream, Category.MUSIC, musicName);
		resources.add(music);
		return music;
	}
	
	protected StreamedAudioResource loadMusic(final String musicName,
											  final float volume) {
		
		final StreamedAudioResource music = new StreamedAudioResource(audioStream, Category.MUSIC, musicName, volume);
		resources.add(music);
		return music;
	}
	
	protected StreamedAudioResource loadMusic(final String musicName,
											  final boolean loop) {
		
		final StreamedAudioResource music = new StreamedAudioResource(audioStream, Category.MUSIC, musicName, loop);
		resources.add(music);
		return music;
	}
	
	protected StreamedAudioResource loadMusic(final String musicName,
											  final float volume,
											  final boolean loop) {
		
		final StreamedAudioResource music = new StreamedAudioResource(audioStream, Category.MUSIC, musicName, volume, loop);
		resources.add(music);
		return music;
	}

	protected StreamedAudioResource loadStreamedSound(final String soundName) {
		final StreamedAudioResource sound = new StreamedAudioResource(audioStream, Category.SOUND, soundName);
		resources.add(sound);
		return sound;
	}
	
	protected StreamedAudioResource loadStreamedSound(final String soundName,
													  final float volume) {
		
		final StreamedAudioResource sound = new StreamedAudioResource(audioStream, Category.SOUND, soundName, volume);
		resources.add(sound);
		return sound;
	}
	
	protected StreamedAudioResource loadStreamedSound(final String soundName,
													  final boolean loop) {
		
		final StreamedAudioResource sound = new StreamedAudioResource(audioStream, Category.SOUND, soundName, loop);
		resources.add(sound);
		return sound;
	}
	
	protected StreamedAudioResource loadStreamedSound(final String soundName,
													  final float volume,
													  final boolean loop) {
		
		final StreamedAudioResource sound = new StreamedAudioResource(audioStream, Category.SOUND, soundName, volume, loop);
		resources.add(sound);
		return sound;
	}
	
	protected Array<Resource> getResources() {
		return resources;
	}
	
	@Override
	public void dispose() {
		audioStream.dispose();
		
		for(int i = 0; i < resources.size; i += 1) {
			resources.get(i).dispose();
		}
	}
	
}
