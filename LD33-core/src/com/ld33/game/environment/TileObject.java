package com.ld33.game.environment;

import com.ld33.Config;
import com.workforfood.devkit.TextureRegionExt;

public class TileObject extends Tile {

	private float health;
	private float range;
	private float damage;
	private float attackInterval;
	private float timeUntilAttack = 0;
	
	public TileObject(char type, TextureRegionExt textureRegionExt) {
		super(type, textureRegionExt);
		setStats();
	}
	
	private void setStats() {
		if(type == Config.BoltTower) {
			this.health = Config.BoltTowerHealth;
			this.range = Config.BoltTowerRange;
			this.damage = Config.BoltTowerDamage;
			this.attackInterval = Config.BoltTowerAttackInterval;
		}
	}

}
