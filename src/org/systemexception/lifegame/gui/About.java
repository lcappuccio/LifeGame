package org.systemexception.lifegame.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class About extends JFrame {

	private static final long serialVersionUID = -2382552714035702227L;
	private static final int WINDOW_WIDTH = 300, WINDOW_HEIGHT = 100;
	private static final JLabel LABEL_TITLE = new JLabel("LifeGame - Java Conway's Game of Life"),
			LABEL_COPYRIGHT = new JLabel("Copyright © 2014 - Leonardo Cappuccio"), LABEL_LICENSE = new JLabel(
					"Released under GNU GPL v3.0 License");

	/**
	 * Create the frame.
	 */
	public About() {
		setTitle("About LifeGame");
		setResizable(false);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		LABEL_TITLE.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		LABEL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_TITLE.setBounds(6, 6, 286, 16);
		getContentPane().add(LABEL_TITLE);

		LABEL_COPYRIGHT.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_COPYRIGHT.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		LABEL_COPYRIGHT.setBounds(6, 25, 286, 16);
		getContentPane().add(LABEL_COPYRIGHT);

		LABEL_LICENSE.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_LICENSE.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		LABEL_LICENSE.setBounds(6, 46, 286, 16);
		getContentPane().add(LABEL_LICENSE);
	}
}
