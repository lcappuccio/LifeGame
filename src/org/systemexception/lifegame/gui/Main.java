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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.systemexception.lifegame.enums.GameSpeeds;
import org.systemexception.lifegame.menu.FileMenu;
import org.systemexception.lifegame.menu.LifeGameMenu;
import org.systemexception.lifegame.menu.SpeedMenu;

public class Main {

	public static int metaKey, coordX, coordY;
	public static JButton btnReset;
	public static Timer gameTimer;
	public static final Font MENU_FONT = new Font("Lucida Grande", Font.PLAIN, 12);
	private static final int INITIAL_SPEED = GameSpeeds.Horse.getGameSpeed();
	private static final String platform = System.getProperty("os.name").toLowerCase();
	private JFrame mainAppWindow;
	private JPanel centerPanel, lowerPanel;
	private JMenuBar menuBar;
	private JMenu menuLifeGame, menuGameSpeed;
	private JLabel lblLiveCells, lblCountLiveCells, lblIteration, lblCountIteration;
	private JButton btnStart, btnTick, btnStop;
	private FileMenu menuFile;
	private Properties properties;
	private int iterationCounter;
	private Grid grid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					window.mainAppWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
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
		mainAppWindow.setBounds(100, 100, 800, 582);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		coordX = mainAppWindow.getX();
		coordY = mainAppWindow.getY();
		mainAppWindow.setResizable(false);
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		mainAppWindow.getContentPane().add(menuBar);
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

		// CENTER panel
		centerPanel = new JPanel();
		centerPanel.setBounds(5, 25, 791, 496);
		mainAppWindow.getContentPane().add(centerPanel);
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
		lowerPanel.setBounds(6, 525, 390, 29);
		mainAppWindow.getContentPane().add(lowerPanel);

		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStart.setEnabled(false);
				if (gameTimer == null) {
					gameTimer = new Timer(INITIAL_SPEED, taskPerformer);
					gameTimer.start();
				} else {
					gameTimer.restart();
				}
			}
		});
		lowerPanel.add(btnStart);
		btnTick = new JButton("Tick");
		btnTick.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameTimer != null && gameTimer.isRunning()) {
					btnStart.setEnabled(true);
					gameTimer.stop();
				}
				iterateGrid();
			}
		});
		lowerPanel.add(btnTick);
		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		lowerPanel.add(btnStop);
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetGrid();
				stopGame();
			}

		});
		lowerPanel.add(btnReset);

		// Live cells counter
		lblLiveCells = new JLabel("Live Cells:");
		lblLiveCells.setBounds(506, 533, 75, 13);
		mainAppWindow.getContentPane().add(lblLiveCells);
		lblLiveCells.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCountLiveCells = new JLabel(String.valueOf(grid.getTotalLiveCells()));
		lblCountLiveCells.setBounds(593, 533, 51, 13);
		mainAppWindow.getContentPane().add(lblCountLiveCells);
		lblCountLiveCells.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

		// Iteration counter
		lblIteration = new JLabel("Iteration:");
		lblIteration.setBounds(656, 533, 75, 13);
		mainAppWindow.getContentPane().add(lblIteration);
		lblIteration.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCountIteration = new JLabel("0");
		lblCountIteration.setBounds(743, 533, 51, 13);
		mainAppWindow.getContentPane().add(lblCountIteration);
		lblCountIteration.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
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

	private ActionListener taskPerformer = new ActionListener() {
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
		menuFile.menuOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileFilter fileFilter = new FileNameExtensionFilter("LifeGame", "life");
				fileChooser.setFileFilter(fileFilter);
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					List<String> fileContents = new ArrayList<String>();
					try {
						BufferedReader fileReader = new BufferedReader(new FileReader(selectedFile));
						String line;
						// Read board settings
						properties = new Properties();
						while ((line = fileReader.readLine()).startsWith("#")) {
							properties.load(new StringReader(line.replace("#", "")));
						}
						while ((line = fileReader.readLine()) != null) {
							fileContents.add(line);
						}
						fileReader.close();
						grid.setColours(properties.getProperty("theme"));
						grid.repaint();
						// for (int i = 0; i < fileContents.size(); i++) {
						// System.out.print(i + ": ");
						// for (int j = 0; j < fileContents.get(i).length();
						// j++) {
						// System.out.print(fileContents.get(i).charAt(j));
						// }
						// System.out.print("\n");
						// }
					} catch (Exception fileException) {
						System.err.format("Exception occurred trying to read '%s'.", selectedFile);
						fileException.printStackTrace();
					}
				}
			}
		});
	}
}
