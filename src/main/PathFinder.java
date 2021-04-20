/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.util.LinkedList;
import java.util.Queue;

/*
The pathfinder uses breadth first search to flood fill all the tiles starting
at the player's location and marking the distance the tiles are from the player.
(If the tile is not a "blocked" tile). Initially all distances are set at max
value and thus the blocked tiles will never be considered as a possible pathway.

Using the distances, the enemy can then trace the shortest path to the player. 
 */
public class PathFinder {

	private final Enemy enemy;
	private final Player player;
	private final Tile[][] worldMap;

	public PathFinder(Enemy enemy, Player player, Tile[][] worldMap) {
		this.enemy = enemy;
		this.player = player;
		this.worldMap = worldMap;
	}

	public Tile.direction findNextDirection() {
		initDistances();
		floodFillDistances();
		return getNextDirection();
	}

	private void initDistances() {
		for (int i = 0; i < World.MAPHEIGHT; i++) {
			for (int j = 0; j < World.MAPWIDTH; j++) {
				this.worldMap[i][j].distance = Integer.MAX_VALUE;
				this.worldMap[i][j].visited = false;
			}
		}
	}

	private void floodFillDistances() {
		Queue<Tile> tileQueue = new LinkedList<>();
		int dist = 0;

		Tile playerLocation = player.getPlayerLocation();
		playerLocation.distance = dist;
		tileQueue.add(playerLocation);
		playerLocation.visited = true;

		Tile last;
		while (!tileQueue.isEmpty()) {
			last = tileQueue.poll();
			last.distance = dist++;

			for (Tile.direction dir : Tile.direction.values()) {
				Tile neighbourTile = last.getSurroundingTile(dir);
				if (!player.blockedTile(neighbourTile) && !neighbourTile.visited) {
					neighbourTile.distance = dist;
					neighbourTile.visited = true;
					tileQueue.add(neighbourTile);
				}
			}
		}
	}

	private Tile.direction getNextDirection() {
		Tile.direction nextDir = null;

		Tile enemyLocation = enemy.getEnemyLocation();
		int lowest = Integer.MAX_VALUE;

		for (Tile.direction dir : Tile.direction.values()) {
			Tile nextTile = enemyLocation.getSurroundingTile(dir);
			if (!enemy.blockedTile(nextTile) && nextTile.distance < lowest) {
				lowest = nextTile.distance;
				nextDir = dir;
			}
		}
		return nextDir;
	}
}
