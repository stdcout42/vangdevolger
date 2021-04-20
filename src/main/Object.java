/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;

public abstract class Object {

	abstract void draw(Graphics g, int x, int y);

	abstract void move(Tile.direction dir);

	public boolean blockedIn(Tile tileToCheck) {
		int count = 0;
		for (Tile.direction dir : Tile.direction.values()) {
			if (blockedTile(tileToCheck.getSurroundingTile(dir))) {
				count++;
			}
		}
		return count == 4;
	}

	public boolean blockedTile(Tile tile) {
		return (tile.getTileObject() instanceof Box)
						|| (tile.getTileObject() instanceof Tree)
						|| (tile.getTileObject() instanceof Enemy)
						|| (tile.getTileObject() instanceof InvisibilityDrink)
						|| (tile instanceof Border);
	}
}
