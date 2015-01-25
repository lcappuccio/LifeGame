/*
    Java implementation of Conway's Game of Life
    Copyright (C) 2014 Leonardo Cappuccio <leo@systemexception.org>

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
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main {

	private JFrame mainAppWindow;
	private String platform = System.getProperty("os.name").toLowerCase();
	private JMenuBar menuBar;
	private JPanel centerPanel, lowerPanel;
	private Grid grid;
	private Preferences prefs;
	private JLabel lblLiveCells;
	private JLabel lblCountLiveCells;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainAppWindow = new JFrame();
		mainAppWindow.setTitle("LifeGame" + " - " + platform);
		mainAppWindow.setBounds(100, 100, 800, 600);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
		mainAppWindow.setResizable(false);
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		mainAppWindow.getContentPane().add(menuBar);
		menuBar.setBorderPainted(false);
		JMenu menuBarFile = new JMenu("File");
		menuBar.add(menuBarFile);
		menuBarFile.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		// ABOUT menu
		JMenuItem menuFileAbout = new JMenuItem("About");
		menuFileAbout.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setBounds(mainAppWindow.getX() + 50, mainAppWindow.getY() + 50, about.getWidth(),
						about.getHeight());
				about.setVisible(true);
			}
		});
		menuFileAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.META_MASK));
		menuBarFile.add(menuFileAbout);

		// PREFERENCES menu
		prefs = new Preferences();
		JMenuItem menuFilePrefs = new JMenuItem("Preferences");
		menuFilePrefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.setVisible(true);
				prefs.setBounds(mainAppWindow.getX() + 40, mainAppWindow.getY() + 40, prefs.getWidth(),
						prefs.getHeight());
			}
		});
		menuFilePrefs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, InputEvent.META_MASK));
		menuBarFile.add(menuFilePrefs);

		// QUIT menu
		JMenuItem menuFileQuit = new JMenuItem("Quit");
		menuFileQuit.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuFileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_MASK));
		menuBarFile.add(menuFileQuit);

		// CENTER panel
		centerPanel = new JPanel();
		centerPanel.setBounds(5, 25, 791, 496);
		mainAppWindow.getContentPane().add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));
		grid = new Grid(prefs.getCellSize(), centerPanel.getWidth() / prefs.getCellSize(), centerPanel.getHeight()
				/ prefs.getCellSize());
		centerPanel.add(grid);
		// LOWER panel
		lowerPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) lowerPanel.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		lowerPanel.setBounds(6, 533, 788, 30);
		mainAppWindow.getContentPane().add(lowerPanel);
		// Iterate button
		JButton btnIterate = new JButton("Iterate");
		btnIterate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
				iterateGrid();
			}
		});
		lowerPanel.add(btnIterate);
		// Stop button
		JButton btnStop = new JButton("Stop");
		lowerPanel.add(btnStop);
		// Stop button
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblCountLiveCells.setText(String.valueOf(grid.getTotalLiveCells()));
				resetGrid();
			}
		});
		lowerPanel.add(btnReset);

		lblLiveCells = new JLabel("Live Cells:");
		lowerPanel.add(lblLiveCells);
		lblCountLiveCells = new JLabel("0");
		lowerPanel.add(lblCountLiveCells);
	}

	public void iterateGrid() {
		grid.iterateBoard();
		centerPanel.repaint();
	}

	public void resetGrid() {
		grid.setCellValue(prefs.getCellSize());
		grid.resetBoard();
		centerPanel.repaint();
	}
}
