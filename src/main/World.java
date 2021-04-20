/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS
 */
package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class World extends javax.swing.JPanel {

	static final int MAPWIDTH = 10;
	static final int MAPHEIGHT = 10;

	private final ImageIcon startImage;
	private final ImageIcon gameWonImage;
	private final ImageIcon gameOverImage;

	private final Sounds sounds;
	private boolean sound;

	private final int OBJDIMENSION;

	private final int NUMBOX;
	private final int NUMTREE;
	private final int NUMFLOWERS = 1;

	private final Player player;
	private final Enemy enemy;

	private final Timer worldTimer;
	private Timer enemyTimer;

	private int enemySpeed;

	MenuChoice menuChoice;
	Tile[][] worldMap;
	PathFinder pathFinder;

	private boolean gamePaused;
	private boolean gameStarted;

	public World() {
		/*
		Game images
		 */
		this.gameOverImage = new ImageIcon(getClass().getResource("/res/game_over.gif"));
		this.gameWonImage = new ImageIcon(getClass().getResource("/res/game_won.gif"));
		this.startImage = new ImageIcon(getClass().getResource("/res/start_animation.gif"));

		/*
		Game music
		 */
		this.sounds = new Sounds();
		this.sound = true;

		/*
		Game elements
		 */
		this.OBJDIMENSION = 70;
		this.NUMTREE = 5;
		this.NUMBOX = 20;

		this.worldMap = new Tile[MAPHEIGHT][MAPWIDTH];
		this.gameStarted = false;

		this.player = new Player();
		this.enemy = new Enemy();

		initComponents();

		this.worldTimer = new Timer(25, this.redraw);
		this.worldTimer.start();

		this.enemySpeed = 700;
		this.enemyTimer = new Timer(this.enemySpeed, this.moveEnemy);
	}

	ActionListener redraw = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			World.this.repaint();
		}
	};

	ActionListener moveEnemy = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			Tile.direction pathDir = pathFinder.findNextDirection();
			if (player.isInvisible()) {

				enemy.move(null);
				if (enemy.touchingPlayer()) {
					enemy.resetTouchingPlayer();
				}
			} else if (!enemy.touchingPlayer()) {
				enemy.move(pathDir);
			}
		}
	};

	public void setMenuChoice(MenuChoice c) {
		this.menuChoice = c;
		if (c == MenuChoice.PAUSE) {
			this.gamePaused = !this.gamePaused;
			if (gamePaused) {
				stopGame();
			} else if (!gamePaused) {
				resumeGame();
			}
		} else if (c == MenuChoice.START) {
			this.gameStarted = true;
			this.gamePaused = false;
		} else if (c == MenuChoice.HARD && !gamePaused) {
			stopGame();
			this.enemySpeed = 160;
			resumeGame();
		} else if (c == MenuChoice.MEDIUM && !gamePaused) {
			stopGame();
			this.enemySpeed = 700;
			resumeGame();
		} else if (c == MenuChoice.EASY && !gamePaused) {
			stopGame();
			this.enemySpeed = 1700;
			resumeGame();
		} else if (c == MenuChoice.SOUND) {
			this.sound = !this.sound;
			stopGame();
			if (!this.gamePaused) {
				resumeGame();
			}
		}
	}

	private void stopGame() {
		enemyTimer.stop();
		if (!sound) {
			this.sounds.stopMusic();
		}
		removeKeyListener(this.player);
	}

	private void resumeGame() {
		addKeyListener(this.player);
		enemyTimer = new Timer(this.enemySpeed, this.moveEnemy);
		enemyTimer.start();
		if (sound) {
			this.sounds.startMusic();
		}
		requestFocus();
	}

	private void initWorld() {
		for (int i = 0; i < MAPHEIGHT; i++) {
			for (int j = 0; j < MAPWIDTH; j++) {
				this.worldMap[i][j] = new Tile();
				if (i == MAPHEIGHT - 1 || j == MAPWIDTH - 1) {
					// Making sure the enemy is never partially/completely boxed in at the 
					// start.
					this.worldMap[i][j].inUse = true;
				} else {
					this.worldMap[i][j].inUse = false;
				}
			}
		}
	}

	private void connectTiles() {
		//...setSurroundingTiles(west, north, east, south);
		for (int i = 0; i < MAPHEIGHT; i++) {
			for (int j = 0; j < MAPWIDTH; j++) {
				if (i == 0) {
					if (j == 0) {
						this.worldMap[i][j].setSurroundingTiles(new Border(), new Border(), this.worldMap[i][j + 1], this.worldMap[i + 1][j]); // top left corner					
					} else if (j == MAPWIDTH - 1) {
						this.worldMap[i][j].setSurroundingTiles(this.worldMap[0][j - 1], new Border(), new Border(), this.worldMap[i + 1][j]); // top right corner
					} else {
						this.worldMap[i][j].setSurroundingTiles(this.worldMap[i][j - 1], new Border(), this.worldMap[i][j + 1], this.worldMap[i + 1][j]); //top borders (not corner)
					}
				} else if (i == MAPHEIGHT - 1) {
					if (j == 0) {
						this.worldMap[i][j].setSurroundingTiles(new Border(), this.worldMap[i - 1][j], this.worldMap[i][j + 1], new Border()); // bottom left corner						
					} else if (j == MAPWIDTH - 1) {
						this.worldMap[i][j].setSurroundingTiles(this.worldMap[i][j - 1], this.worldMap[i - 1][j], new Border(), new Border()); // bottom right corner
					} else {
						this.worldMap[i][j].setSurroundingTiles(this.worldMap[i][j - 1], this.worldMap[i - 1][j], this.worldMap[i][j + 1], new Border()); // bottom borders (not corner)
					}
				} else if (j == 0) {
					this.worldMap[i][j].setSurroundingTiles(new Border(), this.worldMap[i - 1][j], this.worldMap[i][j + 1], this.worldMap[i + 1][j]); // left borders (not corner)
				} else if (j == MAPWIDTH - 1) {
					this.worldMap[i][j].setSurroundingTiles(this.worldMap[i][j - 1], this.worldMap[i - 1][j], new Border(), this.worldMap[i + 1][j]); // right borders (not corner)
				} else {
					this.worldMap[i][j].setSurroundingTiles(this.worldMap[i][j - 1], this.worldMap[i - 1][j], this.worldMap[i][j + 1], this.worldMap[i + 1][j]);
				}
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.gameStarted) {
			drawWorld(g);
		} else {
			drawStartScreen(g);
		}
	}

	private void initPlayerEnemy() {
		this.worldMap[0][0].setTileObject(this.player);
		this.player.setPlayerLocation(this.worldMap[0][0]);
		this.worldMap[0][0].inUse = true;
		removeKeyListener(this.player);
		addKeyListener(this.player);
		requestFocus();

		this.worldMap[MAPHEIGHT - 1][MAPWIDTH - 1].setTileObject(this.enemy);
		this.enemy.setEnemyLocation(this.worldMap[MAPHEIGHT - 1][MAPWIDTH - 1]);
		this.worldMap[MAPHEIGHT - 1][MAPWIDTH - 1].inUse = true;
	}

	private void initBoxesTrees() {
		int itemsPlaced = 0;
		int i, j;
		Random random = new Random();
		while (itemsPlaced < NUMBOX) {
			i = random.nextInt(MAPHEIGHT);
			j = random.nextInt(MAPWIDTH);
			if (!this.worldMap[i][j].inUse) {
				this.worldMap[i][j].setTileObject(new Box());
				this.worldMap[i][j].inUse = true;
				itemsPlaced++;
			}
		}
		itemsPlaced = 0;
		while (itemsPlaced < NUMTREE) {
			i = random.nextInt(MAPHEIGHT);
			j = random.nextInt(MAPWIDTH);
			if (!this.worldMap[i][j].inUse) {
				this.worldMap[i][j].setTileObject(new Tree());
				this.worldMap[i][j].inUse = true;
				itemsPlaced++;
			}
		}
		itemsPlaced = 0;
		while (itemsPlaced < NUMFLOWERS) {
			i = random.nextInt(MAPHEIGHT);
			j = random.nextInt(MAPWIDTH);
			if (!this.worldMap[i][j].inUse) {
				this.worldMap[i][j].setTileObject(new InvisibilityDrink());
				this.worldMap[i][j].inUse = true;
				itemsPlaced++;
			}
		}
	}

	public void fillWorld() {
		this.player.resetInvisibility();
		stopGame();
		resumeGame();
		do {
			initWorld();
			connectTiles();
			initPlayerEnemy();
			initBoxesTrees();
		} while (this.playerWon());

		this.enemy.resetTouchingPlayer();
		this.pathFinder = new PathFinder(enemy, player, worldMap);
	}

	private void drawWorld(Graphics g) {
		if (playerWon()) {
			g.drawImage(gameWonImage.getImage(), (MAPHEIGHT * 70) / 6, (MAPWIDTH * 70) / 4, null);
			g.setColor(Color.BLACK);
			g.setFont(new java.awt.Font("DialogInput", 1, 20));
			g.drawString("YOU WIN !!", (MAPWIDTH * 70) / 5, (MAPHEIGHT * 70) / 3);
		} else if (enemy.touchingPlayer() && !player.isInvisible()) {
			g.drawImage(gameOverImage.getImage(), (MAPWIDTH * 70) / 6, (MAPHEIGHT * 70) / 4, null);
		} else {
			for (int i = 0; i < MAPHEIGHT; i++) {
				for (int j = 0; j < MAPWIDTH; j++) {
					this.worldMap[i][j].draw(g, j * OBJDIMENSION, i * OBJDIMENSION);
				}
			}
			if (this.player.isInvisible()) {
				g.setColor(Color.red);
				g.setFont(new java.awt.Font("DialogInput", 1, 14));
				g.drawString("Invisiblity is active!!", MAPWIDTH * 70 - 300, MAPHEIGHT * 70 - 20);
			} else if (this.player.hasInvisibilityDrink()) {
				g.setColor(Color.red);
				g.setFont(new java.awt.Font("DialogInput", 1, 14));
				g.drawString("Press 'E' to activate invisibility", MAPWIDTH * 70 - 350, MAPHEIGHT * 70 - 20);
			}
		}
	}

	private void drawStartScreen(Graphics g) {
		g.drawImage(startImage.getImage(), 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new java.awt.Font("DialogInput", 1, 10));
		g.drawString("Saman Shahbazi", MAPWIDTH * 70 - 130, MAPHEIGHT * 70 - 20);
	}

	public boolean playerWon() {
		Tile enemyLocation = enemy.getEnemyLocation();
		return this.enemy.blockedIn(enemyLocation);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 700, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 700, Short.MAX_VALUE)
    );
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
