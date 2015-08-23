package com.ld33.game.pawn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;
import com.ld33.App;
import com.ld33.Config;
import com.ld33.game.ManagerInterface;
import com.ld33.game.ProjectileManager;
import com.ld33.game.environment.MapData;
import com.ld33.game.environment.Tile;

public final class PawnManager implements ManagerInterface {

	private static final Vector2 tmpVector = new Vector2();
	
	private final App app;
	private final Player player;
	private final MapData mapData;
	private Group contentGroup;
	private ProjectileManager projectileManager;
	
	private final Array<EnemyMinion> enemyMinions = new Array<EnemyMinion>();
	
	private float boundsWidth;
	private float boundsHeight;
	
	public PawnManager(final App app,
					   final MapData mapData) {
		
		this.app = app;
		this.mapData = mapData;
		this.player = new Player(app);
		
		final float tileWidth = app.getAssets().tileWidth;
		final float tileHeight = app.getAssets().tileHeight;
		
		player.setPosition(
				tileWidth * mapData.getStartX() + tileWidth / 2f - player.getWidth() / 2f,
				tileHeight * mapData.getStartY() + tileHeight / 2f - player.getHeight() / 2f);
		
		for(int i = 0; i < Config.PlayerInitialMinions; i += 1) {
			final Minion minion = new Minion(app, player);
			player.registerMinion(minion);
		}
		
		for(int i = 0; i < player.getMinionCount(); i += 1) {
			player.getMinion(i).begin();
		}
	}
	
	public void addEnemyMinion(float spawnX, float spawnY) {
		final EnemyMinion minion = new EnemyMinion(app, player);
		minion.setPosition(spawnX, spawnY);
		enemyMinions.add(minion);
		contentGroup.addActor(minion);
	}
	
	public void setBounds(final float boundsWidth,
						  final float boundsHeight) {
		
		this.boundsWidth = boundsWidth;
		this.boundsHeight = boundsHeight;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public int getEnemyMinionCount() {
		return enemyMinions.size;
	}
	
	public EnemyMinion getEnemyMinion(final int index) {
		return enemyMinions.get(index);
	}
	
	private boolean checkCollision(final Pawn pawn,
								   final float tileWidth,
								   final float tileHeight) {
		
		final float pawnX = pawn.getX();
		final float pawnY = pawn.getPlaneY();
		final float pawnWidth = pawn.getWidth();
		final float pawnHeight = pawn.getCollisionHeight();
		
		final int leftTile = (int)(pawnX / tileWidth);
		final int rightTile = (int)((pawnX + pawnWidth) / tileWidth);
		final int bottomTile = (int)(pawnY / tileHeight);
		final int topTile = (int)((pawnY + pawnHeight) / tileHeight);
		
		for(int ix = leftTile; ix <= rightTile; ix += 1) {
			for(int iy = bottomTile; iy <= topTile; iy += 1) {
				final Tile tile = mapData.getTileAtXYIndex(ix, iy);
				
				if(!tile.isCollidable()) {
					continue;
				}

				if(pawnX > tile.getRight() || pawnX + pawnWidth < tile.getX() ||
				   pawnY > tile.getTop() || pawnY + pawnHeight < tile.getY()) {
					
					continue;
				}
				
				return true;
			}
		}
	
		return false;
	}

	public void update(final float delta) {
		// Handle controls
		{
			if(Gdx.input.justTouched()) {
				if(player.canAttack()) {
					player.resetAttackCooldown();
					System.out.println("player attack!");
					Vector2 screenClickXY = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());
					Vector2 worldClickXY = contentGroup.stageToLocalCoordinates(screenClickXY);
					projectileManager.createFriendlyAttack(player, worldClickXY.x, worldClickXY.y);
				}
			}
			
			if(Gdx.input.isKeyJustPressed(Config.MoveUpKey)) {
				player.startMoveUp();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveDownKey)) {
				player.startMoveDown();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveLeftKey)) {
				player.startMoveLeft();
			}
			if(Gdx.input.isKeyJustPressed(Config.MoveRightKey)) {
				player.startMoveRight();
			}
			
			if(!Gdx.input.isKeyPressed(Config.MoveUpKey)) {
				player.stopMoveUp();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveDownKey)) {
				player.stopMoveDown();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveLeftKey)) {
				player.stopMoveLeft();
			}
			if(!Gdx.input.isKeyPressed(Config.MoveRightKey)) {
				player.stopMoveRight();
			}
			
			if(Gdx.input.isKeyJustPressed(Config.FormationLargeKey)) {
				player.activateLargeMinionRadius();
			}
			if(Gdx.input.isKeyJustPressed(Config.FormationMidKey)) {
				player.activateMidMinionRadius();
			}
			if(Gdx.input.isKeyJustPressed(Config.FormationSmallKey)) {
				player.activateSmallMinionRadius();
			}
		}

		final float tileWidth = app.getAssets().tileWidth;
		final float tileHeight = app.getAssets().tileHeight;
		
		// Move the player and check if movement was valid
		{
			final float validX = player.getX();
			final float validY = player.getPlaneY();
			
			// X axis
			//player.moveBy(delta * Config.PlayerTilesPerSecond * tileWidth * player.getHorizontalMovementState(), 0f);
			player.moveBy(delta * player.getMovementSpeed() * tileWidth * player.getHorizontalMovementState(), 0f);
			
			if(checkCollision(player, tileWidth, tileHeight)) {
				player.setX(validX);
			}
			
			// Y axis
			//player.moveBy(0f, delta * Config.PlayerTilesPerSecond * tileHeight * player.getVerticalMovementState());
			player.moveBy(0f, delta * player.getMovementSpeed() * tileHeight * player.getVerticalMovementState());
			
			if(checkCollision(player, tileWidth, tileHeight)) {
				player.setY(validY + player.getJumpDisplacement());
			}
		}
		
		// Move minions
		{
			for(int i = 0; i < player.getMinionCount(); i += 1) {
				final Minion minion = player.getMinion(i);
				
				if(minion.getState() != Minion.State.moving) {
					continue;
				}
				
				final float validX = minion.getX();
				final float validY = minion.getPlaneY();
				
				final float requiredAmountX = minion.calcTargetX() - validX;
				final float requiredAmountY = minion.calcTargetY() - validY;
				
				tmpVector.set(requiredAmountX, requiredAmountY);
				tmpVector.nor();
				
//				final float amountX = delta * tileWidth * Config.MinionTilesPerSecond * minion.getMovementSpeedScaler() * tmpVector.x;
//				final float amountY = delta * tileHeight * Config.MinionTilesPerSecond * minion.getMovementSpeedScaler() * tmpVector.y;
				final float amountX = delta * tileWidth * minion.getMovementSpeed() * minion.getMovementSpeedScaler() * tmpVector.x;
				final float amountY = delta * tileHeight * minion.getMovementSpeed() * minion.getMovementSpeedScaler() * tmpVector.y;
				
				if(requiredAmountX != 0f) {
					final float scaleX = Math.min(Math.abs(amountX) / Math.abs(requiredAmountX), 1f);
					minion.moveBy(scaleX * requiredAmountX, 0f);
					
					if(checkCollision(minion, tileWidth, tileHeight)) {
						minion.setX(validX);
					}	
				}
				
				if(requiredAmountY != 0f) {
					final float scaleY = Math.min(Math.abs(amountY) / Math.abs(requiredAmountY), 1f);
					minion.moveBy(0f, scaleY * requiredAmountY);

					if(checkCollision(minion, tileWidth, tileHeight)) {
						minion.setY(validY + minion.getJumpDisplacement());
					}
				}
			}
			//Enemy minions
			for(EnemyMinion enemyMinion : enemyMinions) {
				if(!enemyMinion.isActive()) continue;
//				enemyMinion.moveBy(Config.EnemyMinionTilesPerSecond*delta*MathUtils.cosDeg(enemyMinion.getDirection())*tileWidth,
//						Config.EnemyMinionTilesPerSecond*delta*MathUtils.sinDeg(enemyMinion.getDirection())*tileWidth);
				enemyMinion.moveBy(enemyMinion.getMovementSpeed()*delta*MathUtils.cosDeg(enemyMinion.getDirection())*tileWidth,
						enemyMinion.getMovementSpeed()*delta*MathUtils.sinDeg(enemyMinion.getDirection())*tileWidth);
			}
		}
		
		// Limit player movement to within map bounds
		{
			if(player.getRight() > boundsWidth) {
				player.setX(boundsWidth - player.getWidth());
			}
			else if(player.getX() < 0f) {
				player.setX(0f);
			}
			
			// Special handling for y because of the animation
			final float playerY = player.getPlaneY();
			final float playerTop = playerY + player.getHeight();
			
			if(playerTop > boundsHeight) {
				player.setY(boundsHeight - player.getHeight() + player.getJumpDisplacement());
			}
			else if(playerY < 0f) {
				player.setY(player.getJumpDisplacement());
			}
		}
	}
	
	public void addProjectileManager(ProjectileManager projectileManager) {
		this.projectileManager = projectileManager;
	}

	public void populate(final Group destination) {
		this.contentGroup = destination;
		
		destination.addActor(player);
		
		for(int i = 0; i < player.getMinionCount(); i += 1) {
			destination.addActor(player.getMinion(i));
		}
	}
	
}
