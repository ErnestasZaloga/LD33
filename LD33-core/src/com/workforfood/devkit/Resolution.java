package com.workforfood.devkit;

import com.badlogic.gdx.Gdx;

public final class Resolution {
	
	public static final Resolution LDPI = new Resolution(320, 480, 320, 480, "ldpi", 1024);
	public static final Resolution MDPI = new Resolution(480, 800, 480, 800, "mdpi", 2048);
	public static final Resolution HDPI = new Resolution(800, 1280, 800, 1280, "hdpi", 2048);
	public static final Resolution XHDPI = new Resolution(1600, 2560, 1600, 2560, "xhdpi", 4096);
	
	public static final Resolution[] LIST = new Resolution[] {
		LDPI,
		MDPI,
		HDPI,
		XHDPI
	};
	
	public static Resolution resolve(final int width, 
									 final int height, 
									 final Resolution[] supportedTypes) {
						
		if(supportedTypes == null) {
			throw new IllegalArgumentException("supportedTypes cannot be null.");
		}
		if(supportedTypes.length == 0) {
			throw new IllegalArgumentException("supportedTypes cannot be empty.");
		}
		
		final int size = width > height ? height : width;
		Resolution bestResolution = supportedTypes[0];
		float bestResolutionScale = Math.abs(bestResolution.getResolveScale(size) - 1);
		
		for(final Resolution resolution : supportedTypes) {
			final float resolutionScale = Math.abs(resolution.getResolveScaleFromSize(size) - 1);
			if(resolutionScale <= bestResolutionScale) {
				bestResolutionScale = resolutionScale;
				bestResolution = resolution;
			}
		}
		
		return bestResolution;
	}
	
	public final int width;
	public final int height;
	public final int maxTextureSize;
	public final String name;
	
	private final int resolveWidth;
	private final int resolveHeight;
	
	public Resolution(final int width, 
					  final int height,
					  final int resolveWidth,
					  final int resolveHeight,
					  final String name,
					  final int maxTextureSize) {
		
		this.width = width;
		this.height = height;
		this.resolveWidth = resolveWidth;
		this.resolveHeight = resolveHeight;
		this.name = name;
		this.maxTextureSize = maxTextureSize;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public float getScale() {
		final int width = Gdx.graphics.getWidth();
		final int height = Gdx.graphics.getHeight();
		
		return getScale(width > height ? height : width);
	}
	
	public float getScale(final Resolution resolution) {
		return getScaleFromSize(resolution.getSmallerDimension());
	}
	
	/**
	 * Returns the scale of size from resolution.
	 * */
	public float getScale(final int size) {
		return (float)size / (float)(height > width ? width : height);
	}

	private float getResolveScale(final int size) {
		return (float)size / (float)(resolveHeight > resolveWidth ? resolveWidth : resolveHeight);
	}
	
	/**
	 * Returns the scale of resolution from size.
	 * */
	public float getScaleFromSize(final int size) {
		return (float)getSmallerDimension() / (float)size;
	}
	
	/**
	 * Returns the scale of resolution from size.
	 * */
	private float getResolveScaleFromSize(final int size) {
		return (float)(resolveWidth > resolveHeight ? resolveHeight : resolveWidth) / (float)size;
	}
	
	public int getSmallerDimension() {
		return height > width ? width : height;
	}
	
	public int getLargerDimension() {
		return height > width ? height : width;
	}
	
}
