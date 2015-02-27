package org.systemexception.lifegame.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class About extends JFrame {

	private static final long serialVersionUID = -2382552714035702227L;
	private final int windowWidth = 300, windowHeight = 100;
	private final JLabel labelTitle = new JLabel("LifeGame - Java Conway's Game of Life"),
			labelCopyright = new JLabel("Copyright © 2014 - Leonardo Cappuccio"), labelLicense = new JLabel(
					"Released under GNU GPL v3.0 License");

	/**
	 * Create the frame.
	 */
	public About() {
		setTitle("About LifeGame");
		setResizable(false);
		setBounds(100, 100, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		labelTitle.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(6, 6, 286, 16);
		getContentPane().add(labelTitle);

		labelCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		labelCopyright.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelCopyright.setBounds(6, 25, 286, 16);
		getContentPane().add(labelCopyright);

		labelLicense.setHorizontalAlignment(SwingConstants.CENTER);
		labelLicense.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelLicense.setBounds(6, 46, 286, 16);
		getContentPane().add(labelLicense);
	}
}
