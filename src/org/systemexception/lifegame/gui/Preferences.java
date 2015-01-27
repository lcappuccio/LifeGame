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
	// TODO add algorithm selection and cell life probability

	private GroupLayout gl_prefsWindow;
	private JPanel prefsWindow;
<<<<<<< HEAD
	private JSpinner prefsCellSpinner, prefsCellProbability;
	private JLabel prefsCellSize, lblTheme, lblCellLifeProbability;
	private JButton prefsSave, prefsCancel;
=======
	private JSpinner prefsCellSpinner;
	private JLabel prefsCellSize, lblTheme;
	private JButton prefsApply, prefsCancel;
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
	private JComboBox<String> prefsThemeSelector;
	private SpinnerNumberModel prefsCellSpinnerModel, prefsLifeSpinnerModel;
	private static int cellSize = 5, cellLifeProbability = 50;
	private static final int WINDOW_WIDTH = 265, WINDOW_HEIGHT = 250, MIN_CELL_SIZE = 2, MAX_CELL_SIZE = 10,
			MIN_LIFE_PROBABILITY = 0, MAX_LIFE_PROBABILITY = 100;
	private static String colourTheme = "B & W";

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

<<<<<<< HEAD
	public static int getCellLifeProbability() {
		return cellLifeProbability;
	}

	private void setCellLifeProbability() {
		Preferences.cellLifeProbability = Integer.parseInt(prefsCellProbability.getValue().toString());
=======
	private void applyPrefs() {
		setCellSize();
		setColorTheme();
		super.setVisible(false);
	}

	private void closeWindow() {
		super.setVisible(false);
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
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
		prefsCellSpinnerModel = new SpinnerNumberModel(cellSize, MIN_CELL_SIZE, MAX_CELL_SIZE, 1);
		prefsCellSpinner = new JSpinner(prefsCellSpinnerModel);

		prefsSave = new JButton("Save");
		prefsSave.addActionListener(new ActionListener() {
			// Save cell value
		prefsApply = new JButton("Apply");
		prefsApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				savePrefs();
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

		lblCellLifeProbability = new JLabel("Cell Life Probability");
		prefsLifeSpinnerModel = new SpinnerNumberModel(cellLifeProbability, MIN_LIFE_PROBABILITY, MAX_LIFE_PROBABILITY,
				1);
		prefsCellProbability = new JSpinner(prefsLifeSpinnerModel);

		gl_prefsWindow = new GroupLayout(prefsWindow);
<<<<<<< HEAD
		gl_prefsWindow.setHorizontalGroup(gl_prefsWindow
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_prefsWindow.createSequentialGroup().addContainerGap()
								.addComponent(prefsSave, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(
						gl_prefsWindow.createSequentialGroup().addContainerGap()
								.addComponent(prefsCancel, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
								.addContainerGap())
				.addGroup(
						gl_prefsWindow
								.createSequentialGroup()
								.addGroup(
										gl_prefsWindow.createParallelGroup(Alignment.LEADING)
												.addComponent(prefsCellSize).addComponent(lblCellLifeProbability)
												.addComponent(lblTheme))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_prefsWindow
												.createParallelGroup(Alignment.LEADING)
												.addComponent(prefsCellProbability, GroupLayout.DEFAULT_SIZE, 127,
														Short.MAX_VALUE)
												.addComponent(prefsCellSpinner, Alignment.TRAILING,
														GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
												.addComponent(prefsThemeSelector, 0, 127, Short.MAX_VALUE))));
=======
		gl_prefsWindow.setHorizontalGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING).addGroup(
				gl_prefsWindow
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.LEADING)
										.addGroup(
												gl_prefsWindow
														.createSequentialGroup()
														.addGroup(
																gl_prefsWindow.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTheme)
																		.addComponent(prefsCellSize))
														.addGap(18)
														.addGroup(
																gl_prefsWindow
																		.createParallelGroup(Alignment.TRAILING)
																		.addComponent(prefsThemeSelector, 0, 97,
																				Short.MAX_VALUE)
																		.addComponent(prefsCellSpinner,
																				Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE, 97,
																				Short.MAX_VALUE)))
										.addGroup(
												gl_prefsWindow
														.createSequentialGroup()
														.addComponent(prefsApply, GroupLayout.DEFAULT_SIZE, 76,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86,
																GroupLayout.PREFERRED_SIZE))).addContainerGap()));
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
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
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCellLifeProbability)
										.addComponent(prefsCellProbability, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsThemeSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
<<<<<<< HEAD
										.addComponent(lblTheme)).addGap(39).addComponent(prefsSave)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(prefsCancel).addGap(12)));
		prefsWindow.setLayout(gl_prefsWindow);
	}

	private void savePrefs() {
		setCellSize();
		setColorTheme();
		setCellLifeProbability();
		super.setVisible(false);
	}

	private void closeWindow() {
		super.setVisible(false);
	}
=======
										.addComponent(lblTheme))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsApply)
										.addComponent(prefsCancel)).addContainerGap(46, Short.MAX_VALUE)));
		prefsWindow.setLayout(gl_prefsWindow);
	}
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
}
