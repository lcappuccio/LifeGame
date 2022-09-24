package org.systemexception.lifegame.pojo;

import org.systemexception.lifegame.enums.SavedBoardProperties;
import org.systemexception.lifegame.gui.GridGui;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.gui.PreferencesGui;
import org.systemexception.lifegame.menu.FileMenu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FileUtils {

	private static final String EMPTY_STRING = "";

	public static GridGui gridGuiFromFile(final File file) throws IOException {

		List<List<String>> gridGuiAsArrayList = new ArrayList<>();
		String line;
		Properties properties = new Properties();
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		while ((line = fileReader.readLine()) != null) {
			if (!line.startsWith(FileMenu.FILE_PROPERTIES_LINE)) {
				gridGuiAsArrayList.add(getBoardLineFrom(line));
			} else {
				properties.load(new StringReader(line.replace(FileMenu.FILE_PROPERTIES_LINE, EMPTY_STRING)));
			}
		}
		int cellSize = Integer.parseInt(properties.getProperty(SavedBoardProperties.CELLSIZE.toString()));
		int gridCols = Integer.parseInt(properties.getProperty(SavedBoardProperties.COLS.toString()));
		int gridRows = Integer.parseInt(properties.getProperty(SavedBoardProperties.ROWS.toString()));

		String automata = properties.getProperty(SavedBoardProperties.AUTOMATA.toString());
		PreferencesGui.setLifeAutomata(automata);

		String iterationCounter = properties.getProperty(SavedBoardProperties.ITERATION_COUNTER.toString());
		MainGui.lblCountIteration.setText(iterationCounter);

		String theme = properties.getProperty(SavedBoardProperties.THEME.toString());

		return new GridGui(cellSize, gridRows, gridCols, gridGuiAsArrayList, theme);
	}

	private static ArrayList<String> getBoardLineFrom(String line) {
		ArrayList<String> fileLine = new ArrayList<>();
		for (int i = 0; i < line.length(); i++) {
			fileLine.add(String.valueOf(line.charAt(i)));
		}
		return fileLine;
	}
}
