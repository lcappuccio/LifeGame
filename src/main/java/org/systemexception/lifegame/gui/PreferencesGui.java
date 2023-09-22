package org.systemexception.lifegame.gui;

import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.enums.BoardSizes;
import org.systemexception.lifegame.enums.Themes;
import org.systemexception.lifegame.menu.LifeGameMenu;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.util.Objects;

public class PreferencesGui extends JFrame {

	public static final JButton prefsApply = new JButton("Apply");
    public static final JButton prefsCancel = new JButton("Cancel");
	public static final JComboBox<String> prefsBoardSizeSelector = new JComboBox<>();

	private static final int WINDOW_WIDTH = 250;
    private static final int WINDOW_HEIGHT = 240;
    private static final int MIN_CELL_SIZE = 1;
    private static final int MAX_CELL_SIZE = 4;
    private static final int MIN_CELL_LIFE_PROBABILITY = 0;
    private static final int MAX_CELL_LIFE_PROBABILITY = 100;
	private static int cellSize = 2;
    private static int cellLifeProbability = 50;
	private static String colourTheme = Themes.BW.toString();
    private static Automata lifeAutomata = Automata.CONWAY;
    private static String boardSize = BoardSizes.MEDIUM.toString();
	private final JComboBox<String> prefsThemeSelector;
    private final JComboBox<Automata> prefsAutomataSelector;
	private final JSpinner prefsCellSpinner;
    private final JSpinner prefsLifeProbabilitySpinner;

	public static int getCellSize() {
		return cellSize;
	}

	private synchronized void setCellSize() {
		cellSize = Integer.parseInt(prefsCellSpinner.getValue().toString());
	}

	public static String getColorTheme() {
		return colourTheme;
	}

	private synchronized void setColorTheme() {
		PreferencesGui.colourTheme = String.valueOf(prefsThemeSelector.getSelectedItem());
	}

	public static Automata getLifeAutomata() {
		return lifeAutomata;
	}

	private synchronized void setLifeAutomata() {
		PreferencesGui.lifeAutomata = Automata.valueOf(Objects.requireNonNull(prefsAutomataSelector.getSelectedItem()).toString());
	}

	public static void setLifeAutomata(Automata automata) {
		PreferencesGui.lifeAutomata = automata;
	}

	public static int getCellLifeProbability() {
		return cellLifeProbability;
	}

	private synchronized void setCellLifeProbability() {
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
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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

	private JComboBox<String> getThemeJComboBox() {

		JComboBox<String> objectJComboBox = new JComboBox<>();

		objectJComboBox.addItem(Themes.BW.toString());
		objectJComboBox.addItem(Themes.INVERSE.toString());
		objectJComboBox.addItem(Themes.BLUE.toString());
		objectJComboBox.addItem(Themes.GREEN.toString());
		objectJComboBox.addItem(Themes.RED.toString());
		return objectJComboBox;
	}

	private JComboBox<Automata> getAutomataJComboBox() {

		JComboBox<Automata> objectJComboBox = new JComboBox<>();

		objectJComboBox.addItem(Automata.ASSIMILATION);
		objectJComboBox.addItem(Automata.CONWAY);
		objectJComboBox.addItem(Automata.CORAL);
		objectJComboBox.addItem(Automata.DRYLIFE);
		objectJComboBox.addItem(Automata.HIGHLIFE);
		objectJComboBox.addItem(Automata.LIVEFREEORDIE);
		objectJComboBox.addItem(Automata.MAZE);
		objectJComboBox.addItem(Automata.MOVE);
		objectJComboBox.addItem(Automata.SERVIETTES);
		objectJComboBox.setSelectedItem(Automata.CONWAY);
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
