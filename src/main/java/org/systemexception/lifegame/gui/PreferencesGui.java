package org.systemexception.lifegame.gui;

import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.enums.BoardSizes;
import org.systemexception.lifegame.enums.Themes;
import org.systemexception.lifegame.menu.LifeGameMenu;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class PreferencesGui extends JFrame {

	private final JSpinner prefsCellSpinner, prefsLifeProbabilitySpinner;
	private final JComboBox<String> prefsThemeSelector, prefsAutomataSelector;
	public static final JComboBox<String> prefsBoardSizeSelector = new JComboBox<>();
	public static final JButton prefsApply = new JButton("Apply"), prefsCancel = new JButton("Cancel");
	private static int cellSize = 5, cellLifeProbability = 50;
	private static final int WINDOW_WIDTH = 250, WINDOW_HEIGHT = 240, MIN_CELL_SIZE = 1, MAX_CELL_SIZE = 10,
			MIN_CELL_LIFE_PROBABILITY = 0, MAX_CELL_LIFE_PROBABILITY = 100;
	private static String colourTheme = Themes.BW.toString(), lifeAutomata = Automata.CONWAY.toString(),
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

	public static void setBoardSize() {
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
		setTitle(LifeGameMenu.MENU_ITEM_PREFERENCES);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(MainGui.windowPositionX + 25, MainGui.windowPositionY + 25, WINDOW_WIDTH, WINDOW_HEIGHT);
		JPanel prefsWindow = new JPanel();
		prefsWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(prefsWindow);

		SpinnerNumberModel prefsCellSpinnerModel = new SpinnerNumberModel(cellSize, MIN_CELL_SIZE, MAX_CELL_SIZE, 1);
		prefsCellSpinner = new JSpinner(prefsCellSpinnerModel);

		prefsApply.addActionListener(e -> {
			applyPrefs();
			MainGui.btnReset.doClick();
		});
		prefsCancel.addActionListener(e -> closeWindow());

		prefsThemeSelector = getThemeJComboBox();

		prefsAutomataSelector = getAutomataJComboBox();

		SpinnerNumberModel prefsCellLifeProbabilityModel = new SpinnerNumberModel(cellLifeProbability,
				MIN_CELL_LIFE_PROBABILITY, MAX_CELL_LIFE_PROBABILITY, 1);
		prefsLifeProbabilitySpinner = new JSpinner(prefsCellLifeProbabilityModel);

		setUpBoardSizeSelector();
		setGroupLayout(prefsWindow);

	}

	private JComboBox getThemeJComboBox() {

		JComboBox<Object> objectJComboBox = new JComboBox<>();

		objectJComboBox.addItem(Themes.BW.toString());
		objectJComboBox.addItem(Themes.INVERSE.toString());
		objectJComboBox.addItem(Themes.BLUE.toString());
		objectJComboBox.addItem(Themes.GREEN.toString());
		objectJComboBox.addItem(Themes.RED.toString());
		return objectJComboBox;
	}

	private JComboBox getAutomataJComboBox() {

		JComboBox<Object> objectJComboBox = new JComboBox<>();

		objectJComboBox.addItem(Automata.ASSIMILATION.toString());
		objectJComboBox.addItem(Automata.CONWAY.toString());
		objectJComboBox.addItem(Automata.CORAL.toString());
		objectJComboBox.addItem(Automata.DRYLIFE.toString());
		objectJComboBox.addItem(Automata.HIGHLIFE.toString());
		objectJComboBox.addItem(Automata.LIVEFREEORDIE.toString());
		objectJComboBox.addItem(Automata.MAZE.toString());
		objectJComboBox.addItem(Automata.MOVE.toString());
		objectJComboBox.addItem(Automata.SERVIETTES.toString());
		objectJComboBox.setSelectedItem(Automata.CONWAY.toString());
		return objectJComboBox;
	}

	private void setUpBoardSizeSelector() {

		prefsBoardSizeSelector.addItem(BoardSizes.SMALL.toString());
		prefsBoardSizeSelector.addItem(BoardSizes.MEDIUM.toString());
		prefsBoardSizeSelector.addItem(BoardSizes.LARGE.toString());
		prefsBoardSizeSelector.setSelectedItem(BoardSizes.MEDIUM.toString());
	}

	private void setGroupLayout(JPanel jpanel) {

		GroupLayout preferencesWindow = new GroupLayout(jpanel);
		JLabel prefsCellSize = new JLabel("Cell Size");
		JLabel lblTheme = new JLabel("Theme");
		JLabel lblAutomata = new JLabel("Automata");
		JLabel lblLifeProbability = new JLabel("Life Probability");
		JLabel lblBoardSize = new JLabel("Board Size");

		preferencesWindow.setHorizontalGroup(preferencesWindow.createParallelGroup(Alignment.LEADING).addGroup(
				preferencesWindow.createSequentialGroup().addGroup(preferencesWindow
						.createParallelGroup(Alignment.LEADING)
						.addGroup(preferencesWindow.createSequentialGroup()
								.addComponent(prefsApply, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
								.addGap(8).addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86,
										GroupLayout.PREFERRED_SIZE))
						.addGroup(preferencesWindow.createSequentialGroup().addContainerGap()
								.addGroup(preferencesWindow.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTheme).addComponent(prefsCellSize).addComponent(lblAutomata)
										.addComponent(lblLifeProbability).addComponent(lblBoardSize))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(preferencesWindow.createParallelGroup(Alignment.LEADING)
										.addComponent(prefsLifeProbabilitySpinner, GroupLayout.DEFAULT_SIZE, 128,
												Short.MAX_VALUE)
										.addComponent(prefsThemeSelector, 0, 128, Short.MAX_VALUE)
										.addComponent(prefsCellSpinner, GroupLayout.DEFAULT_SIZE, 128,
												Short.MAX_VALUE)
										.addComponent(prefsAutomataSelector, Alignment.TRAILING, 0, 128,
												Short.MAX_VALUE)
										.addComponent(prefsBoardSizeSelector, Alignment.TRAILING, 0, 128,
												Short.MAX_VALUE)))).addContainerGap()));
		preferencesWindow.setVerticalGroup(preferencesWindow.createParallelGroup(Alignment.LEADING).addGroup(
				preferencesWindow.createSequentialGroup().addContainerGap()
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsCellSize)
								.addComponent(prefsCellSpinner, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE).addComponent(lblTheme)
								.addComponent(prefsThemeSelector, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE).addComponent(lblAutomata)
								.addComponent(prefsAutomataSelector, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE)
								.addComponent(prefsLifeProbabilitySpinner, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLifeProbability)).addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE).addComponent(lblBoardSize)
								.addComponent(prefsBoardSizeSelector, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(5)
						.addGroup(preferencesWindow.createParallelGroup(Alignment.BASELINE).addComponent(prefsApply)
								.addComponent(prefsCancel))));
		jpanel.setLayout(preferencesWindow);
	}
}
