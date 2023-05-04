/**
 * @author leo
 * Jan 28, 2015 10:25:38 PM
 */
package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.enums.SavedBoardProperties;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.gui.PreferencesGui;
import org.systemexception.lifegame.model.Board;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileMenu extends JMenu {

    private static final Logger LOGGER = Logger.getLogger(FileMenu.class.getName());
    public static final String FILE_PROPERTIES_LINE = "#";
    public static final String FILE_OPEN = "Open";
    public static final String FILE_SAVE = "Save";
    public JMenuItem menuOpen;
    public JMenuItem menuSave;

    private static final String SAVE_FILE_EXTENSION = ".life";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private transient Board board;

    public FileMenu() {
        this.setFont(MainGui.MENU_FONT);
        UIManager.put("FileChooser.readOnly", Boolean.TRUE);
        this.setText("File");
        this.add(menuOpen());
        this.add(menuSave());
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private JMenuItem menuOpen() {
        menuOpen = new JMenuItem(FILE_OPEN);
        menuOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, MainGui.metaKey));
        return (menuOpen);
    }

    private JMenuItem menuSave() {
        menuSave = new JMenuItem(FILE_SAVE);
        menuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, MainGui.metaKey));
        menuSave.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            fileChooser.showSaveDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file.exists()) {
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to overwrite existing file?", "Confirm", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    try {
                        boolean isFileDeleted = Files.deleteIfExists(file.toPath());
                        if (!isFileDeleted) {
                            JOptionPane.showMessageDialog(null, "Error overwriting file", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            throw new IOException("Error deleting file");
                        }
                    } catch (IOException exception) {
                        LOGGER.log(Level.SEVERE, exception.getMessage());
                    }
                } else {
                    return;
                }
            }
            saveFile(file);
        });
        return (menuSave);
    }

    private void saveFile(final File file) {
        try {
            String fileName = file.getAbsolutePath();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            // Save board properties first
            try (PrintWriter fileWriter = new PrintWriter(bufferedWriter)) {
                // Save board properties first
                final String FILE_PROPERTIES_SEPARATOR = "=";
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.COLS +
                        FILE_PROPERTIES_SEPARATOR + board.getBoardCols() + LINE_SEPARATOR);
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.ROWS +
                        FILE_PROPERTIES_SEPARATOR + board.getBoardRows() + LINE_SEPARATOR);
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.CELLSIZE +
                        FILE_PROPERTIES_SEPARATOR + PreferencesGui.getCellSize() + LINE_SEPARATOR);
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.AUTOMATA +
                        FILE_PROPERTIES_SEPARATOR + PreferencesGui.getLifeAutomata() + LINE_SEPARATOR);
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.ITERATION_COUNTER +
                        FILE_PROPERTIES_SEPARATOR + MainGui.lblCountIteration.getText() + LINE_SEPARATOR);
                fileWriter.print(FILE_PROPERTIES_LINE + SavedBoardProperties.THEME +
                        FILE_PROPERTIES_SEPARATOR + PreferencesGui.getColorTheme() + LINE_SEPARATOR);
                writeToFile(fileWriter);
            }
            if (!fileName.endsWith(SAVE_FILE_EXTENSION)) {
                Files.move(file.toPath(), new File(fileName + SAVE_FILE_EXTENSION).toPath());
            }
        } catch (IOException fileException) {
            String exceptionMessage = fileException.getMessage();
            LOGGER.log(Level.SEVERE, exceptionMessage);
        }
    }

    private void writeToFile(PrintWriter fileWriter) {
        for (int i = 0; i < board.getBoardCols(); i++) {
            for (int j = 0; j < board.getBoardRows(); j++) {
                if (board.getCellAt(j, i).getCellState()) {
                    fileWriter.print(Board.ALIVE_CELL);
                } else {
                    fileWriter.print(Board.DEAD_CELL);
                }
            }
            fileWriter.print(LINE_SEPARATOR);
        }
    }
}
