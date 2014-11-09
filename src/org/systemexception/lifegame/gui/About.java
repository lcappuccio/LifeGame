package org.systemexception.lifegame.gui;

import javax.swing.JFrame;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class About extends JFrame {
	
	private int windowWidth, windowHeight;

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	/**
	 * Create the frame.
	 */
	public About() {
		setType(Type.POPUP);
		setTitle("About LifeGame");
		setResizable(false);
		this.windowWidth = 270;
		this.windowHeight = 85;
		setBounds(100, 100, windowWidth, windowHeight);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JTextPane aboutText = new JTextPane();
		aboutText.setEditable(false);
		aboutText.setText("LifeGame by Leonardo Cappuccio\n\nReleased under GPL License 3.0 - 2014");
		aboutText.setBounds(6, 6, 259, 52);
		aboutText.setOpaque(false);
		getContentPane().add(aboutText);

	}
}
