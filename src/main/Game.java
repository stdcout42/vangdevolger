/*
Vang de volger

@Author: Saman Shahbazi
172 38 47

Leiden University, LIACS

Main class & "launcher" Game.java

Game element sprites & music are from:
https://opengameart.org/

Sprites:	Redshrike
Music:		bojidar-bg
 */
package main;

public class Game extends javax.swing.JFrame {

	MenuChoice menuChoice;

	public Game() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    startButton = new javax.swing.JButton();
    pauseButton = new javax.swing.JButton();
    world = new main.World();
    hardButton = new javax.swing.JButton();
    mediumButton = new javax.swing.JButton();
    easyButton = new javax.swing.JButton();
    settingsLabel = new javax.swing.JLabel();
    soundToggle = new javax.swing.JToggleButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Vang de volger 2.2");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setPreferredSize(new java.awt.Dimension(825, 724));
    setResizable(false);

    startButton.setText("(Re)Start");
    startButton.setToolTipText("Re-shuffle or start the game");
    startButton.setMaximumSize(new java.awt.Dimension(95, 25));
    startButton.setMinimumSize(new java.awt.Dimension(95, 25));
    startButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        startButtonActionPerformed(evt);
      }
    });

    pauseButton.setText("(Un)Pause");
    pauseButton.setToolTipText("Pause the game");
    pauseButton.setPreferredSize(new java.awt.Dimension(86, 25));
    pauseButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pauseButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout worldLayout = new javax.swing.GroupLayout(world);
    world.setLayout(worldLayout);
    worldLayout.setHorizontalGroup(
      worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 700, Short.MAX_VALUE)
    );
    worldLayout.setVerticalGroup(
      worldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 700, Short.MAX_VALUE)
    );

    hardButton.setText("HARD");
    hardButton.setToolTipText("Unbeatable");
    hardButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        hardButtonActionPerformed(evt);
      }
    });

    mediumButton.setText("MEDIUM");
    mediumButton.setToolTipText("Medium difficulty");
    mediumButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        mediumButtonActionPerformed(evt);
      }
    });

    easyButton.setText("EASY");
    easyButton.setToolTipText("For kids, only");
    easyButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        easyButtonActionPerformed(evt);
      }
    });

    settingsLabel.setFont(new java.awt.Font("DialogInput", 1, 10)); // NOI18N
    settingsLabel.setText(" Game Settings");
    settingsLabel.setToolTipText("Adjust difficulty");

    soundToggle.setText("Sound");
    soundToggle.setToolTipText("Toggle sound on/off");
    soundToggle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        soundToggleActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(world, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(mediumButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(easyButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(soundToggle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(hardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(pauseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(settingsLabel)
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addGap(184, 184, 184)
            .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(146, 146, 146)
            .addComponent(settingsLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(hardButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(mediumButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(easyButton)
            .addGap(34, 34, 34)
            .addComponent(soundToggle))
          .addComponent(world, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
		this.world.setMenuChoice(MenuChoice.START);
		this.world.fillWorld();
  }//GEN-LAST:event_startButtonActionPerformed

  private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
		this.world.setMenuChoice(MenuChoice.PAUSE);
  }//GEN-LAST:event_pauseButtonActionPerformed

  private void mediumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumButtonActionPerformed
		this.world.setMenuChoice(MenuChoice.MEDIUM);
  }//GEN-LAST:event_mediumButtonActionPerformed

  private void hardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardButtonActionPerformed
		this.world.setMenuChoice(MenuChoice.HARD);
  }//GEN-LAST:event_hardButtonActionPerformed

  private void easyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyButtonActionPerformed
		this.world.setMenuChoice(MenuChoice.EASY);
  }//GEN-LAST:event_easyButtonActionPerformed

  private void soundToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soundToggleActionPerformed
		this.world.setMenuChoice(MenuChoice.SOUND);
  }//GEN-LAST:event_soundToggleActionPerformed

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Game().setVisible(true);
			}
		});
	}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton easyButton;
  private javax.swing.JButton hardButton;
  private javax.swing.JButton mediumButton;
  private javax.swing.JButton pauseButton;
  private javax.swing.JLabel settingsLabel;
  private javax.swing.JToggleButton soundToggle;
  private javax.swing.JButton startButton;
  private main.World world;
  // End of variables declaration//GEN-END:variables
}
