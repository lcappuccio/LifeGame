/**
 * @author leo
 * Jan 26, 2015 8:20:59 PM
 */
package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.About;
import org.systemexception.lifegame.gui.Main;
import org.systemexception.lifegame.gui.Preferences;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class LifeGameMenu extends JMenu {

	private Preferences preferencesWindow;
	private JMenuItem menuAbout, menuPreferences, menuQuit;

	public LifeGameMenu() {
		this.setFont(Main.MENU_FONT);
		this.setText("LifeGame");
		this.add(menuAbout());
		this.add(menuPreferences());
		this.add(menuQuit());
	}

	private JMenuItem menuAbout() {
		menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(e -> {
			About about = new About();
			about.setBounds(Main.windowPositionX + 25, Main.windowPositionY + 25, about.getWidth(), about.getHeight());
			about.setVisible(true);
		});
		menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Main.metaKey));
		return (menuAbout);
	}

	private JMenuItem menuPreferences() {
		preferencesWindow = new Preferences();
		menuPreferences = new JMenuItem("Preferences");
		menuPreferences.addActionListener(e -> {
			preferencesWindow.setVisible(true);
			preferencesWindow.setBounds(Main.windowPositionX + 25, Main.windowPositionY + 25, preferencesWindow.getWidth(),
					preferencesWindow.getHeight());
		});
		menuPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, Main.metaKey));
		return (menuPreferences);
	}

	private JMenuItem menuQuit() {
		menuQuit = new JMenuItem("Quit");
		menuQuit.addActionListener(e -> System.exit(0));
		menuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Main.metaKey));
		return (menuQuit);
	}
}
