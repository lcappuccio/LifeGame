/**
 * @author leo
 * Jan 26, 2015 8:20:59 PM
 */
package org.systemexception.lifegame.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.systemexception.lifegame.gui.About;
import org.systemexception.lifegame.gui.Main;
import org.systemexception.lifegame.gui.Preferences;

public class LifeGameMenu extends JMenu {

	private static final long serialVersionUID = 7405208334736215552L;
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
		menuAbout.addActionListener(new ActionListener() {
			// About window
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setBounds(Main.coordX + 50, Main.coordY + 50, about.getWidth(), about.getHeight());
				about.setVisible(true);
			}
		});
		menuAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Main.metaKey));
		return (menuAbout);
	}

	private JMenuItem menuPreferences() {
		preferencesWindow = new Preferences();
		menuPreferences = new JMenuItem("Preferences");
		menuPreferences.addActionListener(new ActionListener() {
			// Preferences window
			public void actionPerformed(ActionEvent e) {
				preferencesWindow.setVisible(true);
				preferencesWindow.setBounds(Main.coordX + 40, Main.coordY + 40, preferencesWindow.getWidth(),
						preferencesWindow.getHeight());
			}
		});
		menuPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, Main.metaKey));
		return (menuPreferences);
	}

	private JMenuItem menuQuit() {
		menuQuit = new JMenuItem("Quit");
		menuQuit.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Main.metaKey));
		return (menuQuit);
	}
}
