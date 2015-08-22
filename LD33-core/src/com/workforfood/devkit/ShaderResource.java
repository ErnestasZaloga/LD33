package com.workforfood.devkit;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public final class ShaderResource extends ShaderProgram implements Resource {

	private static String checkString(final String string,
									  final String variableName) {
		
		if(string == null) {
			throw new IllegalArgumentException(variableName + " cannot be null");
		}
		return string;
	}
	
	public ShaderResource(final String vertexFileName,
						  final String fragmentFileName) {
		
		super(Gdx.files.internal("shaders/" + checkString(vertexFileName, "vertexFileName") + ".vert"), Gdx.files.internal("shaders/" + checkString(fragmentFileName, "fragmentFileName") + ".frag"));
	
		if(!super.isCompiled()) {
			System.out.println(super.getLog());
		}
	}
	
}
