package org.systemexception.lifegame.pojo;

import org.systemexception.lifegame.enums.SavedBoardProperties;
import org.systemexception.lifegame.gui.GridGui;
import org.systemexception.lifegame.gui.PreferencesGui;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class FileUtils {

	public static final String SAVE_FILE_EXTENSION = ".life";
	public static final String FILE_PROPERTIES_LINE = "#", FILE_PROPERTIES_SEPARATOR = "=";
	private static final String EMPTY_STRING = "";

	public static GridGui gridGuiFromFile(final File file) throws IOException {

		ArrayList<ArrayList<String>> gridGuiAsArrayList = new ArrayList<>();
		String line;
		Properties properties = new Properties();
		BufferedReader fileReader = new BufferedReader(new FileReader(file));
		while ((line = fileReader.readLine()) != null) {
			if (!line.startsWith(FILE_PROPERTIES_LINE)) {
				ArrayList<String> fileLine = new ArrayList<>();
				for (int i = 0; i < line.length(); i++) {
					fileLine.add(String.valueOf(line.charAt(i)));
				}
				gridGuiAsArrayList.add(fileLine);
			} else {
				properties.load(new StringReader(line.replace(FILE_PROPERTIES_LINE, EMPTY_STRING)));
			}
		}
		int cellSize = Integer.valueOf(properties.getProperty(SavedBoardProperties.CELLSIZE.toString
				()));
		int gridCols = Integer.valueOf(properties.getProperty(SavedBoardProperties.COLS.toString()));
		int gridRows = Integer.valueOf(properties.getProperty(SavedBoardProperties.ROWS.toString()));

		String automata = properties.getProperty(SavedBoardProperties.AUTOMATA.toString());
		PreferencesGui.setLifeAutomata(automata);

		String theme = properties.getProperty(SavedBoardProperties.THEME.toString());

		return new GridGui(cellSize, gridRows, gridCols, gridGuiAsArrayList, theme);
	}
}
