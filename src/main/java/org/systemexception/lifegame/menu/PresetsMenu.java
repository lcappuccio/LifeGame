package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.MainGui;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author leo
 * @date 22/09/15 23:01
 */
public class PresetsMenu extends JMenu {

    private static final Logger LOGGER = Logger.getLogger(PresetsMenu.class.getName());

	public static final String PRESET_7468M = "7468M.life";
    public static final String PRESET_ACORN = "acorn.life";
    public static final String PRESET_B_HEPTOMINO = "b_heptomino.life";
    public static final String PRESET_EMPTY_BOARD = "empty_board.life";
    public static final String PRESET_R_PENTOMINO = "r_pentomino.life";
    public static final String PRESET_RABBITS = "rabbits.life";
    public static final String PRESET_CONWAY_SINGLE_LINE = "single_line_conway.life";
	private static final String PRESETS_FOLDER = "/presets/";
    private static final String TEMP_LIFE_PRESET = "target" + File.separator + "temp.life";

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
                if (inputStream == null) {
                    throw new IOException("Missing presets folder");
                }
				FileOutputStream fileOutputStream = new FileOutputStream(TEMP_LIFE_PRESET);
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
				LOGGER.log(Level.SEVERE, ex.getMessage());
			}
		});
		return jMenuItem;
	}
}
