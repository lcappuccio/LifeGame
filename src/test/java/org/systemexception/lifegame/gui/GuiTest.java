package org.systemexception.lifegame.gui;

import org.junit.BeforeClass;
import org.junit.Test;
import org.systemexception.lifegame.enums.BoardSizes;
import org.systemexception.lifegame.enums.GameSpeeds;
import org.systemexception.lifegame.enums.Themes;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.gui.PreferencesGui;
import org.systemexception.lifegame.menu.FileMenu;
import org.systemexception.lifegame.menu.LifeGameMenu;
import org.systemexception.lifegame.menu.PresetsMenu;
import org.systemexception.lifegame.menu.SpeedMenu;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 27/12/15 13:29
 */
public class GuiTest {

	private static final LifeGameMenu lifeGameMenu = new LifeGameMenu();
	private static final MainGui mainGui = new MainGui();
	private final FileMenu fileMenu = new FileMenu();
	private final PresetsMenu presetsMenu = new PresetsMenu();
	private final SpeedMenu speedMenu = new SpeedMenu();

	@BeforeClass
	public static void setUp() {
		mainGui.main(null);
	}

	@Test
	public void fileMenuTest() {
		assertTrue(2 == fileMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < fileMenu.getItemCount(); i++) {
			JMenuItem jMenuItem = fileMenu.getItem(i);
			menuItems.add(jMenuItem.getText());
		}
		assertTrue(menuItems.contains(FileMenu.FILE_OPEN));
		assertTrue(menuItems.contains(FileMenu.FILE_SAVE));
	}

	@Test
	public void lifeGameMenuTest() {
		assertEquals(3, lifeGameMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < lifeGameMenu.getItemCount(); i++) {
			menuItems.add(lifeGameMenu.getItem(i).getText());
			if (!LifeGameMenu.MENU_ITEM_QUIT.equals(lifeGameMenu.getItem(i).getText())) {
				lifeGameMenu.getItem(i).doClick();
			}
		}
		assertTrue(menuItems.contains(LifeGameMenu.MENU_ITEM_ABOUT));
		assertTrue(menuItems.contains(LifeGameMenu.MENU_ITEM_PREFERENCES));
		assertTrue(menuItems.contains(LifeGameMenu.MENU_ITEM_QUIT));
	}

	@Test
	public void presetsMenuTest() throws IOException {
		assertEquals(7, presetsMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < presetsMenu.getItemCount(); i++) {
			menuItems.add(presetsMenu.getItem(i).getText());
			presetsMenu.getItem(i).doClick();
		}
		assertTrue(menuItems.contains(PresetsMenu.PRESET_7468M));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_ACORN));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_B_HEPTOMINO));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_EMPTY_BOARD));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_R_PENTOMINO));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_RABBITS));
		assertTrue(menuItems.contains(PresetsMenu.PRESET_CONWAY_SINGLE_LINE));
	}

	@Test
	public void speedMenuTest() {
		assertEquals(6, speedMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < speedMenu.getItemCount(); i++) {
			speedMenu.getItem(i).doClick();
			JMenuItem jMenuItem = speedMenu.getItem(i);
			String speedText = jMenuItem.getText();
			menuItems.add(speedText);
		}
		assertTrue(menuItems.contains(GameSpeeds.Neutrino.name()));
		assertTrue(menuItems.contains(GameSpeeds.Cheetah.name()));
		assertTrue(menuItems.contains(GameSpeeds.Horse.name()));
		assertTrue(menuItems.contains(GameSpeeds.Jackrabbit.name()));
		assertTrue(menuItems.contains(GameSpeeds.Llama.name()));
		assertTrue(menuItems.contains(GameSpeeds.Turtle.name()));
	}

	@Test
	public void testStartStop() throws InterruptedException {
		MainGui.btnStart.doClick();
		Thread.sleep(1000);
		MainGui.btnStop.doClick();
	}

	@Test
	public void testReset() {
		MainGui.btnReset.doClick();
	}

	@Test
	public void testSingleIteration() {
		MainGui.btnStep.doClick();
	}

	@Test
	public void testChangeTheme() {
		Themes[] values = Themes.values();
		for (Themes value : values) {
			MainGui.gridGui.setColours(value.toString());
		}
	}

	@Test
	public void testChangeSpeed() throws InterruptedException {
		MainGui.btnStart.doClick();
		Thread.sleep(1000);
		for (int i = 0; i < speedMenu.getItemCount(); i++) {
			speedMenu.getItem(i).doClick();
		}
		MainGui.btnStop.doClick();
	}

	@Test
	public void testBoardSizes() {
		PreferencesGui.prefsBoardSizeSelector.setSelectedItem(BoardSizes.LARGE.toString());
		PreferencesGui.setBoardSize();
		PreferencesGui.prefsApply.doClick();
		PreferencesGui.prefsBoardSizeSelector.setSelectedItem(BoardSizes.MEDIUM.toString());
		PreferencesGui.setBoardSize();
		PreferencesGui.prefsApply.doClick();
		PreferencesGui.prefsBoardSizeSelector.setSelectedItem(BoardSizes.SMALL.toString());
		PreferencesGui.setBoardSize();
		PreferencesGui.prefsApply.doClick();
		PreferencesGui.prefsCancel.doClick();
	}

}
