package org.systemexception.lifegame.gui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class About extends JFrame {

	private static int windowWidth = 270, windowHeight = 90;

	/**
	 * Create the frame.
	 */
	public About() {
		setTitle("About LifeGame");
		setResizable(false);
		setBounds(100, 100, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		JTextPane aboutText = new JTextPane();
		aboutText.setEditable(false);
		aboutText.setBackground(new Color(255, 255, 255, 0));
		aboutText.setText("LifeGame by Leonardo Cappuccio\n\nReleased under GPL License 3.0 - 2014");
		aboutText.setBounds(6, 6, 259, 52);
		getContentPane().add(aboutText);
	}
}
