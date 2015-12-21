/*
 LifeGame
 Copyright (C) 2014 Leonardo Cappuccio

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.systemexception.lifegame.gui;

import org.systemexception.lifegame.enums.BoardSizes;
import org.systemexception.lifegame.enums.GameSpeeds;
import org.systemexception.lifegame.enums.SavedBoardProperties;
import org.systemexception.lifegame.menu.FileMenu;
import org.systemexception.lifegame.menu.LifeGameMenu;
import org.systemexception.lifegame.menu.PresetsMenu;
import org.systemexception.lifegame.menu.SpeedMenu;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {

	public static int metaKey, windowPositionX, windowPositionY;
	public static Timer gameTimer;
	public static final Font MENU_FONT = new Font("Lucida Grande", Font.BOLD, 12);
	private static final Font labelFontBold = new Font("Lucida Grande", Font.BOLD, 10), labelFontPlain = new Font
			("Lucida Grande", Font.PLAIN, 10);
	private static final int INITIAL_SPEED = GameSpeeds.Horse.getGameSpeed();
	private static final String platform = System.getProperty("os.name").toLowerCase();
	private JFrame mainAppWindow;
	private static JPanel centerPanel;
	private JPanel lowerPanel;
	private JMenuBar menuBar;
	private JMenu menuLifeGame, menuGameSpeed, menuPresets;
	private JLabel lblLiveCells, lblIteration;
	private static JLabel lblCountIteration, lblCountLiveCells;
	private JButton btnStart, btnTick;
	public static JButton btnReset, btnStop;
	private FileMenu menuFile;
	private int iterationCounter;
	private static Grid grid;

	/**
	 * Launch the application.
	 *
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Main window = new Main();
				window.mainAppWindow.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		});
	}

	/**
	 * Create the application.
	 */
	private Main() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			// Opt for Nimbus
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
						UnsupportedLookAndFeelException e) {
					e.printStackTrace(System.out);
				}
				break;
			}
			// Set menu accelerator enabler key varies on platform
			if (platform.contains("linux") || platform.contains("windows")) {
				metaKey = InputEvent.CTRL_MASK;
			}
			if (platform.contains("mac")) {
				metaKey = InputEvent.META_MASK;
			}
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainAppWindow = new JFrame();
		mainAppWindow.setTitle("LifeGame" + " - " + platform);
		mainAppWindow.setBounds(0, 0, 1280, 1024);
		mainAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		mainAppWindow.setResizable(false);
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		mainAppWindow.getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setBorderPainted(false);

		// LifeGame menu
		menuLifeGame = new LifeGameMenu();
		menuBar.add(menuLifeGame);
		// File menu
		menuFile = new FileMenu();
		menuBar.add(menuFile);
		menuFileSetOpenAction();
		// Speed menu
		menuGameSpeed = new SpeedMenu();
		menuBar.add(menuGameSpeed);
		// Presets menu
		menuPresets = new PresetsMenu();
		menuBar.add(menuPresets);

		// CENTER panel
		centerPanel = new JPanel();
		centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - 80);
		mainAppWindow.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		grid = new Grid(Preferences.getCellSize(), centerPanel.getWidth() / Preferences.getCellSize(),
				centerPanel.getHeight() / Preferences.getCellSize(), Preferences.getColorTheme());
		menuFile.setBoard(grid.getBoard());
		centerPanel.add(grid);

		// LOWER panel
		lowerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) lowerPanel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		mainAppWindow.getContentPane().add(lowerPanel, BorderLayout.SOUTH);

		btnStart = new JButton("Start");
		btnStart.addActionListener(e -> {
			btnStart.setEnabled(false);
			if (gameTimer == null) {
				gameTimer = new Timer(INITIAL_SPEED, taskPerformer);
				gameTimer.start();
			} else {
				gameTimer.restart();
			}
		});
		lowerPanel.add(btnStart);
		btnTick = new JButton("Tick");
		btnTick.addActionListener(e -> {
			if (gameTimer != null && gameTimer.isRunning()) {
				btnStart.setEnabled(true);
				gameTimer.stop();
			}
			iterateGrid();
		});
		lowerPanel.add(btnTick);
		btnStop = new JButton("Stop");
		btnStop.addActionListener(e -> stopGame());
		lowerPanel.add(btnStop);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(e -> {
			setWindowSize();
			resetGrid();
			stopGame();
		});
		lowerPanel.add(btnReset);

		// Live cells counter
		lblLiveCells = new JLabel("Live Cells:");
		mainAppWindow.getContentPane().add(lblLiveCells, BorderLayout.SOUTH);
		lblLiveCells.setFont(labelFontBold);
		lblCountLiveCells = new JLabel(String.valueOf(grid.getTotalLiveCells()));
		mainAppWindow.getContentPane().add(lblCountLiveCells, BorderLayout.SOUTH);
		lblCountLiveCells.setFont(labelFontPlain);

		// Iteration counter
		lblIteration = new JLabel("Iteration:");
		mainAppWindow.getContentPane().add(lblIteration, BorderLayout.SOUTH);
		lblIteration.setFont(labelFontBold);
		lblCountIteration = new JLabel("0");
		mainAppWindow.getContentPane().add(lblCountIteration, BorderLayout.SOUTH);
		lblCountIteration.setFont(labelFontPlain);

		setWindowSize();
	}

	private void iterateGrid() {
		menuFile.menuSave.setEnabled(true);
		grid.iterateBoard();
		menuFile.setBoard(grid.getBoard());
		iterationCounter++;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private void resetGrid() {
		menuFile.menuSave.setEnabled(true);
		centerPanel.remove(grid);
		grid = new Grid(Preferences.getCellSize(), centerPanel.getWidth() / Preferences.getCellSize(),
				centerPanel.getHeight() / Preferences.getCellSize(), Preferences.getColorTheme());
		grid.resetBoard();
		centerPanel.add(grid);
		iterationCounter = 0;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private void stopGame() {
		menuFile.menuSave.setEnabled(true);
		menuFile.setBoard(grid.getBoard());
		if (gameTimer != null && gameTimer.isRunning()) {
			btnStart.setEnabled(true);
			gameTimer.stop();
		}
	}

	private final ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			menuFile.menuSave.setEnabled(false);
			grid.iterateBoard();
			iterationCounter++;
			lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
			lblCountIteration.setText(String.valueOf(iterationCounter));
		}
	};

	private void menuFileSetOpenAction() {
		menuFile.menuOpen.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter("LifeGame", "life");
			fileChooser.setFileFilter(fileFilter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				openFile(selectedFile);
			}
		});
	}

	public static void openFile(File selectedFile) {
		btnStop.doClick();
		ArrayList<ArrayList<String>> fileContents = new ArrayList<>();
		try {
			Properties properties;
			try (BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile))) {
				String line;
				// Read board settings
				properties = new Properties();
				while ((line = fileReader.readLine()) != null) {
					if (line.startsWith("#")) {
						properties.load(new StringReader(line.replace("#", "")));
					} else {
						ArrayList<String> fileLine = new ArrayList<>();
						for (int i = 0; i < line.length(); i++) {
							fileLine.add(String.valueOf(line.charAt(i)));
						}
						fileContents.add(fileLine);
					}
				}
			}
			int cellSize = Integer.valueOf(properties.getProperty(SavedBoardProperties.CELLSIZE.toString
					()));
			int gridCols = Integer.valueOf(properties.getProperty(SavedBoardProperties.COLS.toString()));
			int gridRows = Integer.valueOf(properties.getProperty(SavedBoardProperties.ROWS.toString()));
			String automata = properties.getProperty(SavedBoardProperties.AUTOMATA.toString());
			String theme = properties.getProperty(SavedBoardProperties.THEME.toString());
			centerPanel.remove(grid);
			grid = new Grid(cellSize, gridRows, gridCols, fileContents, theme);
			centerPanel.add(grid);
			lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
			lblCountIteration.setText("0");
			Preferences.setLifeAutomata(automata);
		} catch (IOException | NumberFormatException fileException) {
			fileException.printStackTrace(System.out);
		}
	}

	private void setWindowSize() {
		if (!mainAppWindow.isVisible()) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			windowPositionX = (screenSize.width - mainAppWindow.getWidth()) / 2;
			windowPositionY = (screenSize.height - mainAppWindow.getHeight()) / 2;
		} else {
			windowPositionX = mainAppWindow.getX();
			windowPositionY = mainAppWindow.getY();
		}
		if (Preferences.getBoardSize().equals(BoardSizes.LARGE.toString())) {
			mainAppWindow.setBounds(windowPositionX, windowPositionY, 1280, 1024);
			centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - 80);
			lowerPanel.setBounds(0, mainAppWindow.getHeight() - 52, 390, 29);
			lblLiveCells.setBounds(986, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountLiveCells.setBounds(1073, mainAppWindow.getHeight() - 52, 75, 29);
			lblIteration.setBounds(1136, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountIteration.setBounds(1223, mainAppWindow.getHeight() - 52, 75, 29);
			return;
		}
		if (Preferences.getBoardSize().equals(BoardSizes.MEDIUM.toString())) {
			mainAppWindow.setBounds(windowPositionX, windowPositionY, 1024, 768);
			centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - 80);
			lowerPanel.setBounds(0, mainAppWindow.getHeight() - 52, 390, 29);
			lblLiveCells.setBounds(700, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountLiveCells.setBounds(787, mainAppWindow.getHeight() - 52, 75, 29);
			lblIteration.setBounds(860, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountIteration.setBounds(947, mainAppWindow.getHeight() - 52, 75, 29);
			return;
		}
		if (Preferences.getBoardSize().equals(BoardSizes.SMALL.toString())) {
			mainAppWindow.setBounds(windowPositionX, windowPositionY, 800, 600);
			centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - 80);
			lowerPanel.setBounds(0, mainAppWindow.getHeight() - 52, 390, 29);
			lblLiveCells.setBounds(506, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountLiveCells.setBounds(593, mainAppWindow.getHeight() - 52, 75, 29);
			lblIteration.setBounds(656, mainAppWindow.getHeight() - 52, 75, 29);
			lblCountIteration.setBounds(743, mainAppWindow.getHeight() - 52, 75, 29);
		}
	}
}
