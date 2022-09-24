/**
 * @author leo
 * Jan 26, 2015 8:20:59 PM
 */
package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.AboutGui;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.gui.PreferencesGui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class LifeGameMenu extends JMenu {

	public static final String MENU_ITEM_ABOUT = "About";
    public static final String MENU_ITEM_PREFERENCES = "Preferences";
    public static final String MENU_ITEM_QUIT = "Quit";

	public LifeGameMenu() {
		this.setFont(MainGui.MENU_FONT);
		this.setText(MainGui.APP_NAME);
		this.add(menuAbout());
		this.add(menuPreferences());
		this.add(menuQuit());
	}

	private JMenuItem menuAbout() {
		JMenuItem menuAbout = new JMenuItem(MENU_ITEM_ABOUT);
		menuAbout.addActionListener(e -> {
			AboutGui aboutGui = new AboutGui();
			aboutGui.setBounds(MainGui.windowPositionX + 25, MainGui.windowPositionY + 25, aboutGui.getWidth(),
					aboutGui.getHeight());
			aboutGui.setVisible(true);
		});
		menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, MainGui.metaKey));
		return (menuAbout);
	}

	private JMenuItem menuPreferences() {
		PreferencesGui preferencesGuiWindow = new PreferencesGui();
		JMenuItem menuPreferences = new JMenuItem(MENU_ITEM_PREFERENCES);
		menuPreferences.addActionListener(e -> {
			preferencesGuiWindow.setVisible(true);
			preferencesGuiWindow.setBounds(MainGui.windowPositionX + 25, MainGui.windowPositionY + 25,
					preferencesGuiWindow.getWidth(), preferencesGuiWindow.getHeight());
		});
		menuPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, MainGui.metaKey));
		return (menuPreferences);
	}

	private JMenuItem menuQuit() {
		JMenuItem menuQuit = new JMenuItem(MENU_ITEM_QUIT);
		menuQuit.addActionListener(e -> System.exit(0));
		menuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, MainGui.metaKey));
		return (menuQuit);
	}
}
