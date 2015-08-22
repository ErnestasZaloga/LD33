package com.ld33.game.pawn;

import com.ld33.App;

public final class Minion extends Pawn {
	
	private final App app;
	private final Player player;
	
	public Minion(final App app,
				  final Player player) {
		
		this.app = app;
		this.player = player;
	}
	
	
}
