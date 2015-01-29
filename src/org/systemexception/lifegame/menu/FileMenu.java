/**
 * @author leo
 * Jan 28, 2015 10:25:38 PM
 */
package org.systemexception.lifegame.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.systemexception.lifegame.gui.Main;
import org.systemexception.lifegame.gui.Preferences;
import org.systemexception.lifegame.model.Board;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 2938775479619929623L;
	private JMenuItem menuOpen;
	public JMenuItem menuSave;
	private Board board;
	private String lineSeparator = System.getProperty("line.separator");
	private static final String savedFileSeparator = "# BOARD STARTS HERE #";

	public FileMenu() {
		this.setText("File");
		this.add(menuOpen());
		this.add(menuSave());
	}

	public void setBoard(Board board) {
		this.board = board;
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
		menuSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showSaveDialog(null);
				File file = chooser.getSelectedFile();
				boolean exists = true;
				// Check if file exists
				if (file != null) {
					exists = file.exists();
				}
				if (exists && file != null) {
					int response = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to override existing file?", "Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.NO_OPTION) {
						// No! What else?
					} else if (response == JOptionPane.YES_OPTION) {
						file.delete();
						saveFile(file);
					}
				} else {
					if (file != null) {
						file.delete();
						saveFile(file);
					}
				}
			}
		});
		return (menuSave);
	}

	private void saveFile(File file) {
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			PrintWriter fileWriter = new PrintWriter(bufferedWriter);
			// Print board properties first
			fileWriter.print("cols=" + board.getBoardCols() + lineSeparator);
			fileWriter.print("rows=" + board.getBoardRows() + lineSeparator);
			fileWriter.print("cellSize=" + Preferences.getCellSize() + lineSeparator);
			fileWriter.print("automata=" + Preferences.getLifeAutomata() + lineSeparator);
			fileWriter.print("theme=" + Preferences.getColorTheme() + lineSeparator);
			fileWriter.print(savedFileSeparator + lineSeparator);
			for (int i = 0; i < board.getBoardCols(); i++) {
				for (int j = 0; j < board.getBoardRows(); j++) {
					if (board.getCellAt(j, i).getCellState()) {
						fileWriter.print("o");
					} else {
						fileWriter.print(".");
					}
				}
				fileWriter.print(lineSeparator);
			}
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
