package org.systemexception.lifegame.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.systemexception.lifegame.enums.GameSpeeds;
import org.systemexception.lifegame.gui.MainGui;
import org.systemexception.lifegame.menu.FileMenu;
import org.systemexception.lifegame.menu.LifeGameMenu;
import org.systemexception.lifegame.menu.PresetsMenu;
import org.systemexception.lifegame.menu.SpeedMenu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author leo
 * @date 27/12/15 13:29
 */
public class GuiTest {

	private final static MainGui mainGui = new MainGui();
	private final FileMenu fileMenu = new FileMenu();
	private final LifeGameMenu lifeGameMenu = new LifeGameMenu();
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
		assert(menuItems.contains("Open"));
		assert(menuItems.contains("Save"));
	}

	@Test
	public void lifeGameMenuTest() {
		assertTrue(3 == lifeGameMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < lifeGameMenu.getItemCount(); i++) {
			menuItems.add(lifeGameMenu.getItem(i).getText());
		}
		assert(menuItems.contains("About"));
		assert(menuItems.contains("Preferences"));
		assert(menuItems.contains("Quit"));
	}

	@Test
	public void presetsMenuTest() {
		assertTrue(7 == presetsMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < presetsMenu.getItemCount(); i++) {
			menuItems.add(presetsMenu.getItem(i).getText());
		}
		assert(menuItems.contains("7468M.life"));
		assert(menuItems.contains("acorn.life"));
		assert(menuItems.contains("b_heptomino.life"));
		assert(menuItems.contains("empty_board.life"));
		assert(menuItems.contains("r_pentomino.life"));
		assert(menuItems.contains("rabbits.life"));
		assert(menuItems.contains("single_line_conway.life"));

		presetsMenu.getItem(0).doClick();
	}

	@Test
	public void speedMenuTest() {
		assertTrue(5 == speedMenu.getItemCount());
		List<String> menuItems = new ArrayList<>();
		for (int i = 0; i < speedMenu.getItemCount(); i++) {
			JMenuItem jMenuItem = speedMenu.getItem(i);
			String speedText = jMenuItem.getText();
			menuItems.add(speedText);
			int speed = GameSpeeds.valueOf(speedText).getGameSpeed();
			jMenuItem.doClick();
		}
		assert(menuItems.contains(GameSpeeds.Cheetah.name()));
		assert(menuItems.contains(GameSpeeds.Horse.name()));
		assert(menuItems.contains(GameSpeeds.Jackrabbit.name()));
		assert(menuItems.contains(GameSpeeds.Llama.name()));
		assert(menuItems.contains(GameSpeeds.Turtle.name()));
	}

}
