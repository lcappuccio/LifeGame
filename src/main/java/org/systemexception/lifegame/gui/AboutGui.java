package org.systemexception.lifegame.gui;

import javax.swing.*;
import java.awt.*;

public class AboutGui extends JFrame {

	private static final int WINDOW_WIDTH = 300;
    private static final int WINDOW_HEIGHT = 100;
	private static final JLabel LABEL_TITLE = new JLabel("LifeGame - Java Conway's Game of Life");
    private static final JLabel LABEL_COPYRIGHT = new JLabel("Copyright © 2014 - Leonardo Cappuccio");
    private static final JLabel	LABEL_LICENSE = new JLabel("Released under GNU GPL v3.0 License");

	/**
	 * Create the frame.
	 */
	public AboutGui() {
		setTitle("About " + MainGui.APP_NAME);
		setResizable(false);
		setBounds(MainGui.windowPositionX + 50, MainGui.windowPositionY + 50, WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		LABEL_TITLE.setFont(new Font(MainGui.FONT_NAME, Font.BOLD, 12));
		LABEL_TITLE.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_TITLE.setBounds(6, 6, 286, 16);
		getContentPane().add(LABEL_TITLE);

		LABEL_COPYRIGHT.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_COPYRIGHT.setFont(new Font(MainGui.FONT_NAME, Font.PLAIN, 10));
		LABEL_COPYRIGHT.setBounds(6, 25, 286, 16);
		getContentPane().add(LABEL_COPYRIGHT);

		LABEL_LICENSE.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_LICENSE.setFont(new Font(MainGui.FONT_NAME, Font.PLAIN, 10));
		LABEL_LICENSE.setBounds(6, 46, 286, 16);
		getContentPane().add(LABEL_LICENSE);
	}
}
