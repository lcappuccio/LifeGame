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
	private JLabel prefsCellSize, lblTheme;
	private JButton prefsSave, prefsCancel;
	private JComboBox<String> prefsThemeSelector;
	private static int cellSize = 5;
	private static final int WINDOW_WIDTH = 190, WINDOW_HEIGHT = 136, MIN_CELL_SIZE = 2, MAX_CELL_SIZE = 10;
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

	private void savePrefs() {
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

		prefsSave = new JButton("Save");
		prefsSave.addActionListener(new ActionListener() {
			// Save cell value
			@Override
			public void actionPerformed(ActionEvent e) {
				savePrefs();
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

		gl_prefsWindow = new GroupLayout(prefsWindow);
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
														.addComponent(prefsSave, GroupLayout.DEFAULT_SIZE, 76,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86,
																GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		gl_prefsWindow.setVerticalGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING).addGroup(
				gl_prefsWindow
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsCellSpinner, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(prefsCellSize))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsThemeSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblTheme))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsSave)
										.addComponent(prefsCancel)).addContainerGap(46, Short.MAX_VALUE)));
		prefsWindow.setLayout(gl_prefsWindow);
	}
}
