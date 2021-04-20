/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class InvisibilityDrink extends Object {

	private final ImageIcon drink = new ImageIcon(getClass().getResource("/res/drink.png"));
	private final int INVISIBILITYDURATION = 6500;
	
	private boolean invisibilityIsActive;
	private long invisibilityActivatedAt;
	

	@Override
	void draw(Graphics g, int x, int y) {
		g.drawImage(drink.getImage(), x, y, null);
	}

	@Override
	void move(Tile.direction dir) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void activateInvisibility() {
		this.invisibilityActivatedAt = System.currentTimeMillis();
		this.invisibilityIsActive = true;
	}

	public boolean invisibilityIsActive() {
		if (!this.invisibilityIsActive) {
			return false;
		}
		long activeFor = System.currentTimeMillis() - invisibilityActivatedAt;
		return activeFor >= 0 && activeFor <= INVISIBILITYDURATION;
	}

	public void deactiveInvisibility() {
		invisibilityIsActive = false;
	}

}
