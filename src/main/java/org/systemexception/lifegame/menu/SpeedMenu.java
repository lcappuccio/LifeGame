/**
 * @author leo
 * Jan 26, 2015 9:35:36 PM
 */
package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.enums.GameSpeeds;
import org.systemexception.lifegame.gui.MainGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SpeedMenu extends JMenu {

	public SpeedMenu() {
		this.setFont(MainGui.MENU_FONT);
		this.setText("Speed");
		for (int i = 0; i < GameSpeeds.values().length; i++) {
			final JMenuItem speedMenuItem = new JMenuItem(GameSpeeds.values()[i].name());
			final int gameSpeed = GameSpeeds.values()[i].getGameSpeed();
			speedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5 - i, MainGui.metaKey));
			speedMenuItem.addActionListener(e -> {
				if (MainGui.gameTimer != null && MainGui.gameTimer.isRunning()) {
					MainGui.gameTimer.setDelay(gameSpeed);
					resetFont();
					speedMenuItem.setFont(MainGui.MENU_FONT.deriveFont(Font.BOLD));
				}
			});
			this.add(speedMenuItem);
		}
	}

	private void resetFont() {
		for (int i = 0; i < this.getItemCount(); i++) {
			this.getItem(i).setFont(MainGui.MENU_FONT.deriveFont(Font.PLAIN));
		}
	}
}
