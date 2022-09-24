package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.MainGui;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author leo
 * @date 22/09/15 23:01
 */
public class PresetsMenu extends JMenu {

	public final static String PRESET_7468M = "7468M.life", PRESET_ACORN = "acorn.life",
			PRESET_B_HEPTOMINO = "b_heptomino.life", PRESET_EMPTY_BOARD = "empty_board.life",
			PRESET_R_PENTOMINO = "r_pentomino.life", PRESET_RABBITS = "rabbits.life",
			PRESET_CONWAY_SINGLE_LINE = "single_line_conway.life";
	private static final String PRESETS_FOLDER = "/presets/",
			TEMP_LIFE_PRESET = "target" + File.separator + "temp.life";

	public PresetsMenu() {
		this.setFont(MainGui.MENU_FONT);
		this.setText("Presets");
		this.add(buildMenuItem(PRESET_7468M));
		this.add(buildMenuItem(PRESET_ACORN));
		this.add(buildMenuItem(PRESET_B_HEPTOMINO));
		this.add(buildMenuItem(PRESET_EMPTY_BOARD));
		this.add(buildMenuItem(PRESET_R_PENTOMINO));
		this.add(buildMenuItem(PRESET_RABBITS));
		this.add(buildMenuItem(PRESET_CONWAY_SINGLE_LINE));
	}

	private JMenuItem buildMenuItem(final String fileName) {
		JMenuItem jMenuItem = new JMenuItem();
		jMenuItem.setText(fileName);
		jMenuItem.addActionListener(actionEvent -> {

			try (InputStream inputStream = this.getClass().getResourceAsStream(PRESETS_FOLDER + fileName)) {
				FileOutputStream fileOutputStream = new FileOutputStream(new File(TEMP_LIFE_PRESET));
				int read;
				byte[] bytes = new byte[1024];
				while ((read = inputStream.read(bytes)) != -1) {
					fileOutputStream.write(bytes, 0, read);
				}
				fileOutputStream.close();
				File tempFile = new File(TEMP_LIFE_PRESET);
				MainGui.openFile(new File(TEMP_LIFE_PRESET));
				tempFile.deleteOnExit();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
		return jMenuItem;
	}
}
