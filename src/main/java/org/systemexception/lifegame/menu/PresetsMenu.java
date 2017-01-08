package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.MainGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private static final String PRESETS_FOLDER = File.separator + "presets" + File.separator,
			TEMP_LIFE_PRESET = "temp.life";

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
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ClassLoader classLoader = this.getClass().getClassLoader();
				InputStream inputStream = classLoader.getResourceAsStream(PRESETS_FOLDER + fileName);
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TEMP_LIFE_PRESET));
					int read = 0;
					byte[] bytes = new byte[1024];
					while ((read = inputStream.read(bytes)) != -1) {
						fileOutputStream.write(bytes, 0, read);
					}
					fileOutputStream.close();
					File tempFile = new File(TEMP_LIFE_PRESET);
					MainGui.openFile(new File(TEMP_LIFE_PRESET));
					tempFile.delete();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return jMenuItem;
	}
}
