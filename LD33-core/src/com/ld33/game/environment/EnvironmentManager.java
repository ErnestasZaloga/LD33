package com.ld33.game.environment;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ld33.Config;
import com.ld33.game.GameWorld;
import com.ld33.game.ManagerInterface;
import com.ld33.game.pawn.PawnManager;
import com.ld33.game.pawn.Player;

public class EnvironmentManager implements ManagerInterface {
	
	private final GameWorld gameWorld;
	private PawnManager pawnManager;
	private MapData mapData;
	
	public EnvironmentManager(final GameWorld gameWorld, PawnManager pawnManager, MapData mapData) {
		this.gameWorld = gameWorld;
		this.pawnManager = pawnManager;
		this.mapData = mapData;
	}
	
	@Override
	public void update(float delta) {
		final Player player = pawnManager.getPlayer();
		final float playerX = player.getX();
		final float playerY = player.getY();
		
		//Update each tower
		for(final TileObject tileObject : mapData.getTileObjects()) {
			final float towerX = tileObject.getX();
			final float towerY = tileObject.getY();
			
			if(distanceBetween(towerX, towerY, playerX, playerY) <= tileObject.getRange()) {
				if(tileObject.canAttack()) {
					if(tileObject.type == Config.BoltTower || tileObject.type == Config.SpawnerTower) {
						tileObject.startCooldown();
						gameWorld.getProjectileManager().createBolt(towerX+tileObject.getWidth()/2, towerY+tileObject.getHeight()/2, playerX+player.getWidth()/2, playerY+player.getHeight()/2);
					}
					else if(tileObject.type == Config.ElementalTower) {
						tileObject.startCooldown();
						if(MathUtils.randomBoolean()) {  //Decide to shoot fire or ice randomly
							//Ice
							gameWorld.getProjectileManager().createIceBolt(towerX+tileObject.getWidth()/2, towerY+tileObject.getHeight()/2, playerX+player.getWidth()/2, playerY+player.getHeight()/2);
							
						} else {
							//Fire
						}
					}
				}
				if(tileObject.canPerformSpecialAction()) {
					tileObject.startSpecialActionCooldown();
					//Calculate spawn coordinates
					Vector2 spawnCoordinates = getSpawnXY(player, tileObject);
					gameWorld.getPawnManager().addEnemyMinion(spawnCoordinates.x, spawnCoordinates.y);
				}
			}
		}
	}
	
	private float distanceBetween(float x1, float y1, float x2, float y2) {
		float dx = x1-x2;
		float dy = y1-y2;
		return (float) Math.sqrt((dx*dx)+(dy*dy));
	}
	
	private Vector2 getSpawnXY(Player player, TileObject tileObject) {
		/*Quarters:
		 1 | 2
		 -----
		 4 | 3
		 */
		Vector2 result = new Vector2();
		float spawnX = 0;
		float spawnY = 0;
		int quarter = 0;
		int[] quarters = {0, 0, 0, 0};
		if(player.getX() <= tileObject.getX()) {
			if(player.getY() <= tileObject.getY()) {
				quarter = 4;
			} else {
				quarter = 1;
			}
		} else {
			if(player.getY() <= tileObject.getY()) {
				quarter = 3;
			} else {
				quarter = 2;
			}
		}
		quarters[0] = quarter;
		
		if((quarter+1) > 4) {
			quarter = 1;
		} else {
			quarter++;
		}
		quarters[1] = quarter;
		
		if((quarter+1) > 4) {
			quarter = 1;
		} else {
			quarter++;
		}
		if((quarter+1) > 4) {
			quarter = 1;
		} else {
			quarter++;
		}
		quarters[2] = quarter;
		
		if((quarter-1) < 1) {
			quarter = 4;
		} else {
			quarter--;
		}
		quarters[3] = quarter;
		
		//Loop through quarters
		for(int i=0; i<quarters.length; i++) {
			if(quarters[i] == 1) {
				Tile t = mapData.getTileAtXYIndex(tileObject.getXIndex()-1, tileObject.getYIndex());
				if(!t.isCollidable()) {
					spawnX = t.getX();
					spawnY = t.getY();
					break;
				}
				Tile t1 = mapData.getTileAtXYIndex(tileObject.getXIndex()-1, tileObject.getYIndex()+1);
				if(!t1.isCollidable()) {
					spawnX = t1.getX();
					spawnY = t1.getY();
					break;
				}
			}
			else if(quarters[i] == 2) {
				Tile t = mapData.getTileAtXYIndex(tileObject.getXIndex(), tileObject.getYIndex()+1);
				if(!t.isCollidable()) {
					spawnX = t.getX();
					spawnY = t.getY();
					break;
				}
				Tile t1 = mapData.getTileAtXYIndex(tileObject.getXIndex()+1, tileObject.getYIndex()+1);
				if(!t1.isCollidable()) {
					spawnX = t1.getX();
					spawnY = t1.getY();
					break;
				}
				Tile t2 = mapData.getTileAtXYIndex(tileObject.getXIndex()+1, tileObject.getYIndex());
				if(!t2.isCollidable()) {
					spawnX = t2.getX();
					spawnY = t2.getY();
					break;
				}
			}
			else if(quarters[i] == 3) {
				Tile t = mapData.getTileAtXYIndex(tileObject.getXIndex()+1, tileObject.getYIndex()-1);
				if(!t.isCollidable()) {
					spawnX = t.getX();
					spawnY = t.getY();
					break;
				}
				Tile t1 = mapData.getTileAtXYIndex(tileObject.getXIndex(), tileObject.getYIndex()-1);
				if(!t1.isCollidable()) {
					spawnX = t1.getX();
					spawnY = t1.getY();
					break;
				}
			}
			else if(quarters[i] == 4) {
				Tile t = mapData.getTileAtXYIndex(tileObject.getXIndex()-1, tileObject.getYIndex()-1);
				if(!t.isCollidable()) {
					spawnX = t.getX();
					spawnY = t.getY();
					break;
				}
			}

		}
		
		result.x = spawnX + mapData.getTileWH()*0.4f;
		result.y = spawnY + mapData.getTileWH()*0.4f;
		return result;
	}
	
}
