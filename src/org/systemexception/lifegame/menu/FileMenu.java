/**
 * @author leo
 * Jan 28, 2015 10:25:38 PM
 */
package org.systemexception.lifegame.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.systemexception.lifegame.gui.Main;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 2938775479619929623L;
	private JMenuItem menuOpen, menuSave;

	public FileMenu() {
		this.setText("File");
		this.add(menuOpen());
		this.add(menuSave());
	}

	private JMenuItem menuOpen() {
		menuOpen = new JMenuItem("Open");
		menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Main.metaKey));
		menuOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				// int result =
				// fileChooser.showOpenDialog(getParent().getComponent(1));
				// if (result == JFileChooser.APPROVE_OPTION) {
				// File selectedFile = fileChooser.getSelectedFile();
				// System.out.println("Selected file: " +
				// selectedFile.getAbsolutePath());
				// }
			}
		});
		return (menuOpen);
	}

	private JMenuItem menuSave() {
		menuSave = new JMenuItem("Save");
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Main.metaKey));
		return (menuSave);
	}
}
