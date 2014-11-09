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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class Main {

	private JFrame mainAppWindow;
	private String platform = System.getProperty("os.name").toLowerCase();
	private JMenuBar menuBar;
	private JPanel centerPanel;
	private Grid grid;
	private Preferences prefs;

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
		mainAppWindow.setBounds(100, 100, 640, 480);
		mainAppWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainAppWindow.getContentPane().setLayout(null);
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
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setBounds(6, 25, mainAppWindow.getWidth() - 10, mainAppWindow.getHeight() - menuBar.getHeight()
				- 50);
		mainAppWindow.getContentPane().add(centerPanel);
		centerPanel.setLayout(new BorderLayout(0, 0));
		grid = new Grid(prefs.getCellSize(), centerPanel.getWidth(), centerPanel.getHeight());
		centerPanel.add(grid, BorderLayout.NORTH);

		// All initialized, add listener to resize
		mainAppWindow.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				// This is only called when the user releases the mouse button.
				redrawOnResize(prefs, centerPanel);
			}
		});
	}

	private void redrawOnResize(Preferences prefs, JPanel centerPanel) {
		menuBar.setBounds(0, 0, mainAppWindow.getWidth(), 20);
		centerPanel.setBounds(6, 25, mainAppWindow.getWidth() - 10, mainAppWindow.getHeight() - menuBar.getHeight()
				- 50);
		grid = new Grid(prefs.getCellSize(), centerPanel.getWidth(), centerPanel.getHeight());
		System.out.println("Main: " + centerPanel.getWidth() + "\t" + centerPanel.getHeight() + "\t"
				+ prefs.getCellSize());
	}
}
