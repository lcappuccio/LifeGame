package org.systemexception.lifegame.gui;

import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.enums.BoardSizes;
import org.systemexception.lifegame.enums.Themes;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class PreferencesGui extends JFrame {

	private final GroupLayout gl_prefsWindow;
	private final JSpinner prefsCellSpinner, prefsLifeProbabilitySpinner;
	private final JLabel prefsCellSize = new JLabel("Cell Size"), lblTheme = new JLabel("Theme"),
			lblAutomata = new JLabel("Automata"), lblLifeProbability = new JLabel("Life Probability"),
			lblBoardSize = new JLabel("Board Size");
	private final JComboBox<String> prefsThemeSelector, prefsAutomataSelector, prefsBoardSizeSelector;
	private static int cellSize = 5, cellLifeProbability = 50;
	private static final int WINDOW_WIDTH = 250, WINDOW_HEIGHT = 240, MIN_CELL_SIZE = 1, MAX_CELL_SIZE = 10,
			MIN_CELL_LIFE_PROBABILITY = 1, MAX_CELL_LIFE_PROBABILITY = 100;
	private static String colourTheme = Themes.BW.toString() ,lifeAutomata = Automata.CONWAY.toString(),
			boardSize = BoardSizes.MEDIUM.toString();

	public static int getCellSize() {
		return cellSize;
	}

	private void setCellSize() {
		PreferencesGui.cellSize = Integer.parseInt(prefsCellSpinner.getValue().toString());
	}

	public static String getColorTheme() {
		return colourTheme;
	}

	private void setColorTheme() {
		PreferencesGui.colourTheme = String.valueOf(prefsThemeSelector.getSelectedItem());
	}

	public static String getLifeAutomata() {
		return lifeAutomata;
	}

	private void setLifeAutomata() {
		PreferencesGui.lifeAutomata = String.valueOf(prefsAutomataSelector.getSelectedItem());
	}

	public static void setLifeAutomata(String automata) {
		PreferencesGui.lifeAutomata = automata;
	}

	public static int getCellLifeProbability() {
		return cellLifeProbability;
	}

	private void setCellLifeProbability() {
		PreferencesGui.cellLifeProbability = Integer.parseInt(prefsLifeProbabilitySpinner.getValue().toString());
	}

	public static String getBoardSize() {
		return boardSize;
	}

	private void setBoardSize() {
		PreferencesGui.boardSize = String.valueOf(prefsBoardSizeSelector.getSelectedItem());
	}

	private void applyPrefs() {
		setCellSize();
		setColorTheme();
		setLifeAutomata();
		setCellLifeProbability();
		setBoardSize();
		super.setVisible(false);
	}

	private void closeWindow() {
		super.setVisible(false);
	}

	/**
	 * Create the frame.
	 */
	public PreferencesGui() {
		setResizable(false);
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(MainGui.windowPositionX + 25, MainGui.windowPositionY + 25, WINDOW_WIDTH, WINDOW_HEIGHT);
		JPanel prefsWindow = new JPanel();
		prefsWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(prefsWindow);

		SpinnerNumberModel prefsCellSpinnerModel = new SpinnerNumberModel(cellSize, MIN_CELL_SIZE, MAX_CELL_SIZE, 1);
		prefsCellSpinner = new JSpinner(prefsCellSpinnerModel);

		JButton prefsApply = new JButton("Apply");
		prefsApply.addActionListener(e -> {
			applyPrefs();
			MainGui.btnReset.doClick();
		});
		JButton prefsCancel = new JButton("Cancel");
		prefsCancel.addActionListener(e -> closeWindow());

		prefsThemeSelector = new JComboBox<>();
		prefsThemeSelector.addItem(Themes.BW.toString());
		prefsThemeSelector.addItem(Themes.INVERSE.toString());
		prefsThemeSelector.addItem(Themes.BLUE.toString());
		prefsThemeSelector.addItem(Themes.GREEN.toString());
		prefsThemeSelector.addItem(Themes.RED.toString());

		prefsAutomataSelector = new JComboBox<>();
		prefsAutomataSelector.addItem(Automata.ASSIMILATION.toString());
		prefsAutomataSelector.addItem(Automata.CONWAY.toString());
		prefsAutomataSelector.addItem(Automata.CORAL.toString());
		prefsAutomataSelector.addItem(Automata.DRYLIFE.toString());
		prefsAutomataSelector.addItem(Automata.HIGHLIFE.toString());
		prefsAutomataSelector.addItem(Automata.LIVEFREEORDIE.toString());
		prefsAutomataSelector.addItem(Automata.MAZE.toString());
		prefsAutomataSelector.addItem(Automata.MOVE.toString());
		prefsAutomataSelector.addItem(Automata.SERVIETTES.toString());
		prefsAutomataSelector.setSelectedItem(Automata.CONWAY.toString());

		prefsBoardSizeSelector = new JComboBox<>();
		prefsBoardSizeSelector.addItem(BoardSizes.SMALL.toString());
		prefsBoardSizeSelector.addItem(BoardSizes.MEDIUM.toString());
		prefsBoardSizeSelector.addItem(BoardSizes.LARGE.toString());
		prefsBoardSizeSelector.setSelectedItem(BoardSizes.MEDIUM.toString());

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
																		.addComponent(lblAutomata)
																		.addComponent(lblLifeProbability)
																		.addComponent(lblBoardSize))
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
																		.addComponent(prefsAutomataSelector,
																				Alignment.TRAILING, 0, 128,
																				Short.MAX_VALUE)
																		.addComponent(prefsBoardSizeSelector,
																				Alignment.TRAILING, 0, 128,
																				Short.MAX_VALUE))))
						.addContainerGap
								()));
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
										.addComponent(lblAutomata)
										.addComponent(prefsAutomataSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(prefsLifeProbabilitySpinner, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblLifeProbability))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_prefsWindow
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblBoardSize)
										.addComponent(prefsBoardSizeSelector, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(5)
						.addGroup(
								gl_prefsWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsApply)
										.addComponent(prefsCancel))));
		prefsWindow.setLayout(gl_prefsWindow);
	}
}
