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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.systemexception.lifegame.menu.FileMenu;
import org.systemexception.lifegame.menu.SpeedMenu;

public class Main {

	private JFrame mainAppWindow;
	private JPanel centerPanel, lowerPanel;
	private JMenuBar menuBar;
	private JMenu menuFile, menuSimulation;
	private JLabel lblLiveCells, lblCountLiveCells, lblIteration, lblCountIteration;
	private JButton btnStart, btnIterate, btnStop, btnReset;
	private Grid grid;
	public static Timer gameTimer;
	private int iterationCounter;
	private static final int INITIAL_SPEED = 210;
	private static String platform = System.getProperty("os.name").toLowerCase();
	public static int metaKey, coordX, coordY;
	public static final Font MENU_FONT = new Font("Lucida Grande", Font.BOLD, 12);

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

		// File menu
		menuFile = new FileMenu();
		menuBar.add(menuFile);
		// Speed menu
		menuSimulation = new SpeedMenu();
		menuBar.add(menuSimulation);

		// CENTER panel
		centerPanel = new JPanel();
		centerPanel.setBounds(5, 25, 791, 496);
		mainAppWindow.getContentPane().add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));
		grid = new Grid(Preferences.getCellSize(), centerPanel.getWidth() / Preferences.getCellSize(),
				centerPanel.getHeight() / Preferences.getCellSize(), Preferences.getColorTheme());
		centerPanel.add(grid);

		// LOWER panel
		lowerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) lowerPanel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		lowerPanel.setBounds(6, 525, 390, 29);
		mainAppWindow.getContentPane().add(lowerPanel);
		btnIterate = new JButton("Iterate");
		btnIterate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameTimer != null && gameTimer.isRunning()) {
					btnStart.setEnabled(true);
					gameTimer.stop();
				}
				iterateGrid();
			}
		});

		btnStart = new JButton("Start");
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lowerPanel.add(btnIterate);
		btnStop = new JButton("Stop");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameTimer != null && gameTimer.isRunning()) {
					btnStart.setEnabled(true);
					gameTimer.stop();
				}
			}
		});
		lowerPanel.add(btnStop);
		btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetGrid();
				if (gameTimer != null && gameTimer.isRunning()) {
					btnStart.setEnabled(true);
					gameTimer.stop();
				}
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
		grid.iterateBoard();
		iterationCounter++;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private void resetGrid() {
		centerPanel.remove(grid);
		grid = new Grid(Preferences.getCellSize(), centerPanel.getWidth() / Preferences.getCellSize(),
				centerPanel.getHeight() / Preferences.getCellSize(), Preferences.getColorTheme());
		grid.resetBoard();
		centerPanel.add(grid);
		iterationCounter = 0;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	private ActionListener taskPerformer = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent evt) {
			grid.iterateBoard();
			iterationCounter++;
			lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
			lblCountIteration.setText(String.valueOf(iterationCounter));
		}
	};
}
