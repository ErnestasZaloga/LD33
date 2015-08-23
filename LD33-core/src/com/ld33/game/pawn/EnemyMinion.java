package com.ld33.game.pawn;

import com.ld33.App;
import com.ld33.Config;

public class EnemyMinion extends Pawn {

	private App app;
	private Player player;
	
	public EnemyMinion(App app, Player player) {
		super(Config.MinionMaxHealth, app.getAssets().depthHeightScaling);
		
		this.app = app;
		this.player = player;
		
		setRegion(app.getAssets().minionRegion);
	}

}
