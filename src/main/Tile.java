/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;
import java.util.EnumMap;

public class Tile {

	private Object object;

	private EnumMap<direction, Tile> surroundingTiles;

	public boolean inUse;
	public boolean visited;
	public int distance;

	public Tile() {
		this.object = new Grass(); // default grass tile
		this.inUse = false;
		this.surroundingTiles = new EnumMap(direction.class);
	}

	public static enum direction {
		WEST, NORTH, EAST, SOUTH;
	}

	public void setSurroundingTiles(Tile west, Tile north, Tile east, Tile south) {
		this.surroundingTiles.put(direction.WEST, west);
		this.surroundingTiles.put(direction.EAST, east);
		this.surroundingTiles.put(direction.NORTH, north);
		this.surroundingTiles.put(direction.SOUTH, south);
	}

	public Tile getSurroundingTile(direction dir) {
		return surroundingTiles.get(dir);
	}

	public void setTileObject(Object obj) {
		this.object = obj;
	}

	public Object getTileObject() {
		return this.object;
	}

	public void draw(Graphics g, int x, int y) {
		this.object.draw(g, x, y);
	}
}
