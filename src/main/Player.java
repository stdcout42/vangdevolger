/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

public class Player extends Object implements KeyListener {

	private final ImageIcon playerImageWest;
	private final ImageIcon playerImageEast;
	private final ImageIcon invisibleImageEast;
	private final ImageIcon invisibleImageWest;
	private Tile playerLocation;
	private Tile.direction charDirection;

	private InvisibilityDrink drink;

	private boolean hasInvisibilityDrink;

	public Player() {
		this.charDirection = Tile.direction.EAST;

		this.playerImageEast = new ImageIcon(getClass().getResource("/res/char_E.png"));
		this.playerImageWest = new ImageIcon(getClass().getResource("/res/char_W.png"));
		this.invisibleImageEast = new ImageIcon(getClass().getResource("/res/invisible_E.png"));
		this.invisibleImageWest = new ImageIcon(getClass().getResource("/res/invisible_W.png"));

		this.drink = new InvisibilityDrink();
		this.hasInvisibilityDrink = false;

	}

	public void setPlayerLocation(Tile location) {
		this.playerLocation = location;
	}

	public Tile getPlayerLocation() {
		return this.playerLocation;
	}

	@Override
	void draw(Graphics g, int x, int y) {
		if (this.charDirection == Tile.direction.EAST) {
			if (this.drink.invisibilityIsActive()) {
				g.drawImage(invisibleImageEast.getImage(), x, y, null);
			} else {
				g.drawImage(playerImageEast.getImage(), x, y, null);
			}
		} else if (this.drink.invisibilityIsActive()) {
			g.drawImage(invisibleImageWest.getImage(), x, y, null);
		} else {
			g.drawImage(playerImageWest.getImage(), x, y, null);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case (KeyEvent.VK_LEFT):
				this.move(Tile.direction.WEST);
				this.charDirection = Tile.direction.WEST;
				break;
			case (KeyEvent.VK_RIGHT):
				this.move(Tile.direction.EAST);
				this.charDirection = Tile.direction.EAST;
				break;
			case (KeyEvent.VK_DOWN):
				this.move(Tile.direction.SOUTH);
				break;
			case (KeyEvent.VK_UP):
				this.move(Tile.direction.NORTH);
				break;
			case (KeyEvent.VK_E):
				if (this.hasInvisibilityDrink) {
					this.drink.activateInvisibility();
					this.hasInvisibilityDrink = false;
				}
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	void move(Tile.direction dir) {
		Tile nextTile = this.playerLocation.getSurroundingTile(dir);
		Object nextTileType = nextTile.getTileObject();

		if (nextTileType instanceof Box) {
			moveBox(dir, nextTile);
		} else if (nextTileType instanceof InvisibilityDrink || !blockedTile(nextTile)) {
			Object player = this.playerLocation.getTileObject();
			if (nextTileType instanceof InvisibilityDrink) {
				this.hasInvisibilityDrink = true;
			}
			this.playerLocation.setTileObject(new Grass());
			nextTile.setTileObject(player);
			setPlayerLocation(nextTile);
		}
	}

	/*
		moveBox()	Plan: 
		Move past the box and find out what's on the other side. Let's call this
		"otherSide. If otherSide is no "hard" object or enemy - the box(es) can be pushed.
		
		This is done by creating a box on the other side & respawning the player on a tile
		in the same respective direction.
	 */
	private void moveBox(Tile.direction dir, Tile boxLocation) {
		Tile otherSide = boxLocation;
		while (otherSide.getTileObject() instanceof Box) {
			otherSide = otherSide.getSurroundingTile(dir);
		}
		if (!blockedTile(otherSide)) {
			Object player = this.playerLocation.getTileObject();
			this.playerLocation.setTileObject(new Grass());
			this.playerLocation = this.playerLocation.getSurroundingTile(dir);
			this.playerLocation.setTileObject(player);
			otherSide.setTileObject(new Box());
		}
	}

	public boolean hasInvisibilityDrink() {
		return this.hasInvisibilityDrink;
	}

	public boolean isInvisible() {
		return this.drink.invisibilityIsActive();
	}

	public void resetInvisibility() {
		this.drink.deactiveInvisibility();
		this.hasInvisibilityDrink = false;
	}
}
