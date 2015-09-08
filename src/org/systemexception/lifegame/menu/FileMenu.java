/**
 * @author leo
 * Jan 28, 2015 10:25:38 PM
 */
package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.enums.SavedBoardProperties;
import org.systemexception.lifegame.gui.Main;
import org.systemexception.lifegame.gui.Preferences;
import org.systemexception.lifegame.model.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileMenu extends JMenu {

	public JMenuItem menuOpen, menuSave;
	private Board board;
	private final String lineSeparator = System.getProperty("line.separator");
	private final String cellAlive = "o", cellDead = ".";

	public FileMenu() {
		this.setFont(Main.MENU_FONT);
		UIManager.put("FileChooser.readOnly", Boolean.FALSE);
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
		return (menuOpen);
	}

	private JMenuItem menuSave() {
		menuSave = new JMenuItem("Save");
		menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Main.metaKey));
		menuSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				fileChooser.showSaveDialog(null);
				File file = fileChooser.getSelectedFile();
				boolean exists = true;
				// Check if file exists
				if (file != null) {
					exists = file.exists();
				}
				if (exists && file != null) {
					int response = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to override existing file?", "Confirm", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (response == JOptionPane.YES_OPTION) {
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
			String fileName = file.getAbsolutePath();
			if (!fileName.endsWith(".life")) {
				file = new File(fileName + ".life");
			}
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			// Save board properties first
			try (PrintWriter fileWriter = new PrintWriter(bufferedWriter)) {
				// Save board properties first
				fileWriter.print("#" + SavedBoardProperties.COLS + "=" + board.getBoardCols() + lineSeparator);
				fileWriter.print("#" + SavedBoardProperties.ROWS + "=" + board.getBoardRows() + lineSeparator);
				fileWriter.print("#" + SavedBoardProperties.CELLSIZE + "=" + Preferences.getCellSize() +
						lineSeparator);
				fileWriter.print("#" + SavedBoardProperties.AUTOMATA + "=" + Preferences.getLifeAutomata() +
						lineSeparator);
				fileWriter.print("#" + SavedBoardProperties.THEME + "=" + Preferences.getColorTheme() + lineSeparator);
				for (int i = 0; i < board.getBoardCols(); i++) {
					for (int j = 0; j < board.getBoardRows(); j++) {
						if (board.getCellAt(j, i).getCellState()) {
							fileWriter.print(cellAlive);
						} else {
							fileWriter.print(cellDead);
						}
					}
					fileWriter.print(lineSeparator);
				}
			}
		} catch (Exception fileException) {
			fileException.printStackTrace(System.out);
		}
	}
}
