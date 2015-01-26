package org.systemexception.lifegame.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class About extends JFrame {

	private static int windowWidth = 270, windowHeight = 90;
	private JLabel labelTitle, lavelCopyright, labelLicense;

	/**
	 * Create the frame.
	 */
	public About() {
		setTitle("About LifeGame");
		setResizable(false);
		setBounds(100, 100, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		labelTitle = new JLabel("LifeGame - Java Conway's Game of Life");
		labelTitle.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(6, 6, 258, 16);
		getContentPane().add(labelTitle);

		lavelCopyright = new JLabel("Copyright © 2014 - Leonardo Cappuccio");
		lavelCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lavelCopyright.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lavelCopyright.setBounds(6, 25, 258, 16);
		getContentPane().add(lavelCopyright);

		labelLicense = new JLabel("Released under GNU GPL v3.0 License");
		labelLicense.setHorizontalAlignment(SwingConstants.CENTER);
		labelLicense.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		labelLicense.setBounds(6, 46, 258, 16);
		getContentPane().add(labelLicense);
	}
}
