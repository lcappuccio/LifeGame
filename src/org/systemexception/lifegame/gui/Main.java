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

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Main {

	private JFrame mainAppWindow;
	private String platform = System.getProperty("os.name").toLowerCase();

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
		mainAppWindow.setBounds(100, 100, 450, 300);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		mainAppWindow.setJMenuBar(menuBar);
		JMenu menuBarFile = new JMenu("File");
		menuBar.add(menuBarFile);
		Preferences prefs = new Preferences();

		// ABOUT menu
		JMenuItem menuFileAbout = new JMenuItem("About");
		menuFileAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.META_MASK));
		menuFileAbout.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setBounds(mainAppWindow.getX() + 50,
						mainAppWindow.getY() + 50, about.getWidth(),
						about.getHeight());
				about.setVisible(true);
			}
		});

		// QUIT menu
		menuBarFile.add(menuFileAbout);
		JMenuItem menuFileQuit = new JMenuItem("Quit");
		menuFileQuit.addActionListener(new ActionListener() {
			// Quit application
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// PREFERENCES menu
		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prefs.setVisible(true);
				prefs.setBounds(mainAppWindow.getX() + 40,
						mainAppWindow.getY() + 40, prefs.getWidth(),
						prefs.getHeight());
			}
		});
		mntmPreferences.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_COMMA, InputEvent.META_MASK));
		menuBarFile.add(mntmPreferences);
		menuFileQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.META_MASK));
		menuBarFile.add(menuFileQuit);
		mainAppWindow.getContentPane().setLayout(null);

		// CENTER panel
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(6, 6, 438, 244);
		mainAppWindow.getContentPane().add(centerPanel);
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		Grid grid = new Grid(prefs.getCellSize(), centerPanel.getWidth(),
				centerPanel.getHeight());
//		grid.setBounds(centerPanel.getX(), centerPanel.getY(),
//				centerPanel.getWidth(), centerPanel.getHeight());
		centerPanel.add(grid);
	}
}
