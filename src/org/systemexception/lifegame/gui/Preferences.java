package org.systemexception.lifegame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Preferences extends JFrame {

	private GroupLayout gl_prefsWindow;
	private JPanel prefsWindow;
	private JSpinner prefsCellSpinner;
	private JLabel prefsCellSize, lblTheme, lblRules;
	private JButton prefsApply, prefsCancel;
	private JComboBox<String> prefsThemeSelector, prefsLifeRules;
	private static int cellSize = 5;
	private static final int WINDOW_WIDTH = 190, WINDOW_HEIGHT = 170, MIN_CELL_SIZE = 2, MAX_CELL_SIZE = 10;
	private static String colourTheme = "B & W", lifeRules = "Conway";

	public static int getCellSize() {
		return cellSize;
	}

	private void setCellSize() {
		Preferences.cellSize = Integer.parseInt(prefsCellSpinner.getValue().toString());
	}

	public static String getColorTheme() {
		return colourTheme;
	}

	private void setColorTheme() {
		Preferences.colourTheme = String.valueOf(prefsThemeSelector.getSelectedItem());
	}

	public static String getLifeRules() {
		return lifeRules;
	}

	public void setLifeRules() {
		Preferences.lifeRules = String.valueOf(prefsLifeRules.getSelectedItem());
	}

	private void applyPrefs() {
		setCellSize();
		setColorTheme();
		super.setVisible(false);
	}

	private void closeWindow() {
		super.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public Preferences() {
		setResizable(false);
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, WINDOW_WIDTH, WINDOW_HEIGHT);
		prefsWindow = new JPanel();
		prefsWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(prefsWindow);

		prefsCellSize = new JLabel("Cell Size");

		SpinnerNumberModel prefsCellSpinnerModel = new SpinnerNumberModel(cellSize, MIN_CELL_SIZE, MAX_CELL_SIZE, 1);
		prefsCellSpinner = new JSpinner(prefsCellSpinnerModel);

		prefsApply = new JButton("Apply");
		prefsApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applyPrefs();
				Main.btnReset.doClick();
			}
		});
		prefsCancel = new JButton("Cancel");
		prefsCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});

		prefsThemeSelector = new JComboBox<String>();
		prefsThemeSelector.addItem("B & W");
		prefsThemeSelector.addItem("Inverse");
		prefsThemeSelector.addItem("Blue");
		prefsThemeSelector.addItem("Green");
		prefsThemeSelector.addItem("Red");
		lblTheme = new JLabel("Theme");

		lblRules = new JLabel("Rules");

		prefsLifeRules = new JComboBox<String>();
		prefsLifeRules.addItem("DryLife");
		prefsLifeRules.addItem("Conway's Life");
		prefsLifeRules.addItem("HighLife");
		prefsLifeRules.addItem("Life Free or Die");
		prefsLifeRules.addItem("Maze");

		gl_prefsWindow = new GroupLayout(prefsWindow);
		gl_prefsWindow.setHorizontalGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING).addGroup(
				gl_prefsWindow
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												Alignment.TRAILING,
												gl_prefsWindow
														.createSequentialGroup()
														.addComponent(prefsApply, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGap(2)
														.addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_prefsWindow
														.createSequentialGroup()
														.addGroup(
																gl_prefsWindow.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTheme)
																		.addComponent(prefsCellSize)
																		.addComponent(lblRules))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(
																gl_prefsWindow
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(prefsThemeSelector, 0, 109,
																				Short.MAX_VALUE)
																		.addComponent(prefsCellSpinner,
																				GroupLayout.DEFAULT_SIZE, 109,
																				Short.MAX_VALUE)
																		.addComponent(prefsLifeRules, 0, 109,
																				Short.MAX_VALUE)))).addContainerGap()));
		gl_prefsWindow.setVerticalGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING).addGroup(
				gl_prefsWindow
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsCellSize)
										.addComponent(prefsCellSpinner, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTheme)
										.addComponent(prefsThemeSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblRules)
										.addComponent(prefsLifeRules, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsApply)
										.addComponent(prefsCancel)).addContainerGap()));
		prefsWindow.setLayout(gl_prefsWindow);
	}
}
