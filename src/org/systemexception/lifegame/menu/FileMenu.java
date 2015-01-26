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

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 7405208334736215552L;
	private Preferences preferencesWindow;
	private JMenuItem menuFileAbout, menuFilePrefs, menuFileQuit;

	public FileMenu() {
		this.setFont(Main.MENU_FONT);
		this.setText("File");
		this.add(menuAbout());
		this.add(menuPreferences());
		this.add(menuQuit());
	}

	private JMenuItem menuAbout() {
		menuFileAbout = new JMenuItem("About");
		menuFileAbout.addActionListener(new ActionListener() {
			// About window
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setBounds(Main.coordX + 50, Main.coordY + 50, about.getWidth(), about.getHeight());
				about.setVisible(true);
			}
		});
		menuFileAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Main.metaKey));
		return (menuFileAbout);
	}

	private JMenuItem menuPreferences() {
		preferencesWindow = new Preferences();
		menuFilePrefs = new JMenuItem("Preferences");
		menuFilePrefs.addActionListener(new ActionListener() {
			// Preferences window
			public void actionPerformed(ActionEvent e) {
				preferencesWindow.setVisible(true);
				preferencesWindow.setBounds(Main.coordX + 40, Main.coordY + 40, preferencesWindow.getWidth(),
						preferencesWindow.getHeight());
			}
		});
		menuFilePrefs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, Main.metaKey));
		return (menuFilePrefs);
	}

	private JMenuItem menuQuit() {
		menuFileQuit = new JMenuItem("Quit");
		menuFileQuit.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Main.metaKey));
		return (menuFileQuit);
	}
}
