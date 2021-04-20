/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends Object {

	private final ImageIcon enemyImageWest;
	private final ImageIcon enemyImageEast;

	private Tile enemyLocation;
	private Tile.direction enemyDirection;

	private boolean touchingPlayer;

	public Enemy() {
		this.enemyImageEast = new ImageIcon(getClass().getResource("/res/enemy_E.png"));
		this.enemyImageWest = new ImageIcon(getClass().getResource("/res/enemy_W.png"));
		this.enemyDirection = Tile.direction.WEST;
		this.touchingPlayer = false;
	}

	@Override
	void draw(Graphics g, int x, int y) {
		if (enemyDirection == Tile.direction.EAST) {
			g.drawImage(enemyImageEast.getImage(), x, y, null);
		} else {
			g.drawImage(enemyImageWest.getImage(), x, y, null);
		}
	}

	@Override
	void move(Tile.direction dir) {
		if (dir == null) {
			Random random = new Random();
			int randomNum = random.nextInt(4);
			dir = Tile.direction.values()[randomNum];
		}
		this.enemyDirection = dir;
		Tile nextTile = this.enemyLocation.getSurroundingTile(dir);
		if (!blockedTile(nextTile) && nextTile.distance <= 1) {
			this.touchingPlayer = true;
		} else if (!blockedTile(nextTile) && !(nextTile.getTileObject() instanceof Player)) {
			Object enemy = this.enemyLocation.getTileObject();
			this.enemyLocation.setTileObject(new Grass());

			nextTile.setTileObject(enemy);
			setEnemyLocation(nextTile);
		}
	}

	public void setEnemyLocation(Tile location) {
		this.enemyLocation = location;
	}

	public Tile getEnemyLocation() {
		return this.enemyLocation;
	}

	public boolean touchingPlayer() {
		return this.touchingPlayer;
	}

	public void resetTouchingPlayer() {
		this.touchingPlayer = false;
	}

}
