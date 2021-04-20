/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Grass extends Object {

	@Override
	void draw(Graphics g, int x, int y) {
		g.drawImage(new ImageIcon(getClass().getResource("/res/grass.png")).getImage(), x, y, null);
	}

	@Override
	void move(Tile.direction dir) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
