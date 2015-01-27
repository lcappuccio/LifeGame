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

public class Preferences extends JFrame {

	private static final long serialVersionUID = 1664253982218939516L;
	private GroupLayout gl_prefsWindow;
	private JPanel prefsWindow;
	private JSpinner prefsCellSpinner, prefsLifeProbabilitySpinner;
	private final JLabel prefsCellSize = new JLabel("Cell Size"), lblTheme = new JLabel("Theme"),
			lblRules = new JLabel("Rules"), lblLifeProbability = new JLabel("Life Probability");
	private JButton prefsApply, prefsCancel;
	private JComboBox<String> prefsThemeSelector, prefsLifeRules;
	private static int cellSize = 5, cellLifeProbability = 50;
	private static final int WINDOW_WIDTH = 250, WINDOW_HEIGHT = 210, MIN_CELL_SIZE = 2, MAX_CELL_SIZE = 10,
			MIN_CELL_LIFE_PROBABILITY = 0, MAX_CELL_LIFE_PROBABILITY = 100;
	private static String colourTheme = "B & W", lifeRules = "Conway's Life";

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

	public static int getCellLifeProbability() {
		return cellLifeProbability;
	}

	private void setCellLifeProbability() {
		Preferences.cellLifeProbability = Integer.parseInt(prefsLifeProbabilitySpinner.getValue().toString());
	}

	private void applyPrefs() {
		setCellSize();
		setColorTheme();
		setLifeRules();
		setCellLifeProbability();
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

		prefsLifeRules = new JComboBox<String>();
		prefsLifeRules.addItem("DryLife");
		prefsLifeRules.addItem("Conway's Life");
		prefsLifeRules.addItem("HighLife");
		prefsLifeRules.addItem("Live Free or Die");
		prefsLifeRules.addItem("Maze");

		SpinnerNumberModel prefsCellLifeProbabilityModel = new SpinnerNumberModel(cellLifeProbability,
				MIN_CELL_LIFE_PROBABILITY, MAX_CELL_LIFE_PROBABILITY, 1);
		prefsLifeProbabilitySpinner = new JSpinner(prefsCellLifeProbabilityModel);

		gl_prefsWindow = new GroupLayout(prefsWindow);
		gl_prefsWindow.setHorizontalGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING).addGroup(
				gl_prefsWindow
						.createSequentialGroup()
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_prefsWindow
														.createSequentialGroup()
														.addComponent(prefsApply, GroupLayout.DEFAULT_SIZE, 140,
																Short.MAX_VALUE)
														.addGap(8)
														.addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86,
																GroupLayout.PREFERRED_SIZE))
										.addGroup(
												gl_prefsWindow
														.createSequentialGroup()
														.addContainerGap()
														.addGroup(
																gl_prefsWindow.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTheme)
																		.addComponent(prefsCellSize)
																		.addComponent(lblRules)
																		.addComponent(lblLifeProbability))
														.addPreferredGap(ComponentPlacement.RELATED)
														.addGroup(
																gl_prefsWindow
																		.createParallelGroup(Alignment.LEADING)
																		.addComponent(prefsLifeProbabilitySpinner,
																				GroupLayout.DEFAULT_SIZE, 128,
																				Short.MAX_VALUE)
																		.addComponent(prefsThemeSelector, 0, 128,
																				Short.MAX_VALUE)
																		.addComponent(prefsCellSpinner,
																				GroupLayout.DEFAULT_SIZE, 128,
																				Short.MAX_VALUE)
																		.addComponent(prefsLifeRules,
																				Alignment.TRAILING, 0, 128,
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
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsLifeProbabilitySpinner, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLifeProbability))
						.addGap(18)
						.addGroup(
								gl_prefsWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsApply)
										.addComponent(prefsCancel))));
		prefsWindow.setLayout(gl_prefsWindow);
	}
}
