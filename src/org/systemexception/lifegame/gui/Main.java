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
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.systemexception.lifegame.menu.FileMenu;

public class Main {

	private JFrame mainAppWindow;
	private JPanel centerPanel, lowerPanel;
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JLabel lblLiveCells, lblCountLiveCells, lblIteration, lblCountIteration;
	private JSlider sliderSpeed;
	private JButton btnStart, btnIterate, btnStop, btnReset;
	private Grid grid;
	private Timer gameTimer;
	private int selectedSpeed, iterationCounter;
	private static final int MAX_SPEED = 10, MIN_SPEED = 500, INITIAL_SPEED = 250;
	private static String PLATFORM = System.getProperty("os.name").toLowerCase();
	public static int metaKey;
	public static Font MENU_FONT = new Font("Lucida Grande", Font.BOLD, 12);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	public Main() {
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
			if (PLATFORM.contains("linux") || PLATFORM.contains("windows")) {
				metaKey = InputEvent.CTRL_MASK;
			}
			if (PLATFORM.contains("mac")) {
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
		mainAppWindow.setTitle("LifeGame" + " - " + PLATFORM);
		mainAppWindow.setBounds(100, 100, 800, 582);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		mainAppWindow.setResizable(false);
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		mainAppWindow.getContentPane().add(menuBar);
		menuBar.setBorderPainted(false);

		// File Menubar
		menuFile = new FileMenu(mainAppWindow.getX(), mainAppWindow.getY());
		menuBar.add(menuFile);

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
		lowerPanel.setBounds(6, 525, 540, 29);
		mainAppWindow.getContentPane().add(lowerPanel);
		sliderSpeed = new JSlider(MAX_SPEED, MIN_SPEED, INITIAL_SPEED);
		sliderSpeed.setMajorTickSpacing(100);
		sliderSpeed.setMinorTickSpacing(50);
		sliderSpeed.setInverted(true);
		sliderSpeed.setSnapToTicks(true);
		sliderSpeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selectedSpeed = sliderSpeed.getValue();
				if (gameTimer != null && gameTimer.isRunning()) {
					gameTimer.setDelay(selectedSpeed);
				}

			}
		});
		sliderSpeed.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				selectedSpeed = sliderSpeed.getValue();
				if (gameTimer != null && gameTimer.isRunning()) {
					gameTimer.setDelay(selectedSpeed);
				}
			}
		});
		lowerPanel.add(sliderSpeed);
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
					selectedSpeed = sliderSpeed.getValue();
					gameTimer.setDelay(selectedSpeed);
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
		lblLiveCells.setBounds(558, 533, 51, 13);
		mainAppWindow.getContentPane().add(lblLiveCells);
		lblLiveCells.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCountLiveCells = new JLabel("0");
		lblCountLiveCells.setBounds(622, 533, 51, 13);
		mainAppWindow.getContentPane().add(lblCountLiveCells);
		lblCountLiveCells.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		// Iteration counter
		lblIteration = new JLabel("Iteration:");
		lblIteration.setBounds(685, 533, 46, 13);
		mainAppWindow.getContentPane().add(lblIteration);
		lblIteration.setFont(new Font("Lucida Grande", Font.BOLD, 10));
		lblCountIteration = new JLabel("0");
		lblCountIteration.setBounds(743, 533, 51, 13);
		mainAppWindow.getContentPane().add(lblCountIteration);
		lblCountIteration.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	}

	public void iterateGrid() {
		grid.iterateBoard();
		centerPanel.repaint();
		iterationCounter++;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	public void resetGrid() {
		centerPanel.remove(grid);
		grid = new Grid(Preferences.getCellSize(), centerPanel.getWidth() / Preferences.getCellSize(),
				centerPanel.getHeight() / Preferences.getCellSize(), Preferences.getColorTheme());
		grid.setCellValue(Preferences.getCellSize());
		grid.resetBoard();
		centerPanel.add(grid);
		centerPanel.repaint();
		iterationCounter = 0;
		lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
		lblCountIteration.setText(String.valueOf(iterationCounter));
	}

	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			grid.iterateBoard();
			centerPanel.repaint();
			iterationCounter++;
			lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
			lblCountIteration.setText(String.valueOf(iterationCounter));
		}
	};
}
