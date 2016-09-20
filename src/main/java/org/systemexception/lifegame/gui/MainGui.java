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

public class MainGui {

	public static int metaKey, windowPositionX, windowPositionY;
	public static Timer gameTimer;
	public static final String FONT_NAME = "Lucida Grande", APP_NAME = "LifeGame";
	public static final Font MENU_FONT = new Font(FONT_NAME, Font.BOLD, 12);
	private static final Font labelFontBold = new Font(FONT_NAME, Font.BOLD, 10), labelFontPlain = new Font
			(FONT_NAME, Font.PLAIN, 10);
	private static final int INITIAL_SPEED = GameSpeeds.Horse.getGameSpeed();
	private static final String platform = System.getProperty("os.name").toLowerCase();
	private final int labelHeight = 29, labelWidth = 75, panelWidth = 390, mainAppWindowHeightExclude = 80,
			panelAndLabelHeightExclude = 52;
	private JFrame mainAppWindow;
	private static JPanel centerPanel;
	private JPanel lowerPanel;
	private JLabel lblLiveCells, lblIteration;
	private static JLabel lblCountIteration, lblCountLiveCells;
	public static JButton btnReset, btnStop, btnStart, btnTick;
	private FileMenu menuFile;
	private int iterationCounter;
	public static GridGui gridGui;

	/**
	 * Launch the application.
	 *
	 * @param args UNUSED
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainGui window = new MainGui();
				window.mainAppWindow.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace(System.out);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGui() {
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
		mainAppWindow.setTitle(APP_NAME + " - " + platform);
		mainAppWindow.setBounds(0, 0, 1280, 1024);
		mainAppWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		mainAppWindow.setResizable(false);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		mainAppWindow.getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setBorderPainted(false);

		// LifeGame menu
		JMenu menuLifeGame = new LifeGameMenu();
		menuBar.add(menuLifeGame);
		// File menu
		menuFile = new FileMenu();
		menuBar.add(menuFile);
		menuFileSetOpenAction();
		// Speed menu
		JMenu menuGameSpeed = new SpeedMenu();
		menuBar.add(menuGameSpeed);
		// Presets menu
		JMenu menuPresets = new PresetsMenu();
		menuBar.add(menuPresets);

		// CENTER panel
		centerPanel = new JPanel();
		centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - mainAppWindowHeightExclude);
		mainAppWindow.getContentPane().add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		gridGui = new GridGui(PreferencesGui.getCellSize(), centerPanel.getWidth() / PreferencesGui.getCellSize(),
				centerPanel.getHeight() / PreferencesGui.getCellSize(), PreferencesGui.getColorTheme());
		menuFile.setBoard(gridGui.getBoard());
		centerPanel.add(gridGui);

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
		lblCountLiveCells = new JLabel(String.valueOf(gridGui.getTotalLiveCells()));
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
		gridGui.iterateBoard();
		menuFile.setBoard(gridGui.getBoard());
		iterationCounter++;
		lblCountLiveCells.setText(String.valueOf(gridGui.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private void resetGrid() {
		menuFile.menuSave.setEnabled(true);
		centerPanel.remove(gridGui);
		gridGui = new GridGui(PreferencesGui.getCellSize(), centerPanel.getWidth() / PreferencesGui.getCellSize(),
				centerPanel.getHeight() / PreferencesGui.getCellSize(), PreferencesGui.getColorTheme());
		gridGui.resetBoard();
		centerPanel.add(gridGui);
		iterationCounter = 0;
		lblCountLiveCells.setText(String.valueOf(gridGui.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private void stopGame() {
		menuFile.menuSave.setEnabled(true);
		menuFile.setBoard(gridGui.getBoard());
		if (gameTimer != null && gameTimer.isRunning()) {
			btnStart.setEnabled(true);
			gameTimer.stop();
		}
	}

	private final ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			menuFile.menuSave.setEnabled(false);
			gridGui.iterateBoard();
			iterationCounter++;
			lblCountLiveCells.setText(String.valueOf(gridGui.getTotalLiveCells()));
			lblCountIteration.setText(String.valueOf(iterationCounter));
		}
	};

	private void menuFileSetOpenAction() {
		menuFile.menuOpen.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter(APP_NAME, "life");
			fileChooser.setFileFilter(fileFilter);
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(fileChooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				try {
					openFile(selectedFile);
				} catch (IOException exception) {
					exception.getMessage();
				}
			}
		});
	}

	public static void openFile(File selectedFile) throws IOException {
		btnStop.doClick();
		ArrayList<ArrayList<String>> fileContents = new ArrayList<>();
		Properties properties;
		BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
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
		int cellSize = Integer.valueOf(properties.getProperty(SavedBoardProperties.CELLSIZE.toString
				()));
		int gridCols = Integer.valueOf(properties.getProperty(SavedBoardProperties.COLS.toString()));
		int gridRows = Integer.valueOf(properties.getProperty(SavedBoardProperties.ROWS.toString()));
		String automata = properties.getProperty(SavedBoardProperties.AUTOMATA.toString());
		String theme = properties.getProperty(SavedBoardProperties.THEME.toString());
		centerPanel.remove(gridGui);
		gridGui = new GridGui(cellSize, gridRows, gridCols, fileContents, theme);
		centerPanel.add(gridGui);
		lblCountLiveCells.setText(String.valueOf(gridGui.getTotalLiveCells()));
		lblCountIteration.setText("0");
		PreferencesGui.setLifeAutomata(automata);
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
		if (PreferencesGui.getBoardSize().equals(BoardSizes.LARGE.toString())) {
			setLargeWindowLayout();
			return;
		}
		if (PreferencesGui.getBoardSize().equals(BoardSizes.MEDIUM.toString())) {
			setMediumWindowLayout();
			return;
		}
		if (PreferencesGui.getBoardSize().equals(BoardSizes.SMALL.toString())) {
			setSmallWindowLayout();
		}
	}

	private void setLargeWindowLayout() {
		mainAppWindow.setBounds(windowPositionX, windowPositionY, 1280, 1024);
		centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - mainAppWindowHeightExclude);
		lowerPanel.setBounds(0, mainAppWindow.getHeight() - panelAndLabelHeightExclude, panelWidth, labelHeight);
		lblLiveCells.setBounds(986, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountLiveCells.setBounds(1073, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
		lblIteration.setBounds(1136, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountIteration.setBounds(1223, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
	}

	private void setMediumWindowLayout() {
		mainAppWindow.setBounds(windowPositionX, windowPositionY, 1024, 768);
		centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - mainAppWindowHeightExclude);
		lowerPanel.setBounds(0, mainAppWindow.getHeight() - panelAndLabelHeightExclude, panelWidth, labelHeight);
		lblLiveCells.setBounds(700, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountLiveCells.setBounds(787, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
		lblIteration.setBounds(860, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountIteration.setBounds(947, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
	}

	private void setSmallWindowLayout() {
		mainAppWindow.setBounds(windowPositionX, windowPositionY, 800, 600);
		centerPanel.setBounds(0, 25, mainAppWindow.getWidth(), mainAppWindow.getHeight() - mainAppWindowHeightExclude);
		lowerPanel.setBounds(0, mainAppWindow.getHeight() - panelAndLabelHeightExclude, panelWidth, labelHeight);
		lblLiveCells.setBounds(506, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountLiveCells.setBounds(593, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
		lblIteration.setBounds(656, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth, labelHeight);
		lblCountIteration.setBounds(743, mainAppWindow.getHeight() - panelAndLabelHeightExclude, labelWidth,
				labelHeight);
	}
}
