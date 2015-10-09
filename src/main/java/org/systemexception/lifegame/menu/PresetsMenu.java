package org.systemexception.lifegame.menu;

import org.systemexception.lifegame.gui.Main;

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

	private static final String PRESETS_FOLDER = File.separator + "presets" + File.separator,
			TEMP_LIFE_PRESET = "temp.life";

	public PresetsMenu() {
		this.setFont(Main.MENU_FONT);
		this.setText("Presets");
		this.add(buildMenuItem("7468M.life"));
		this.add(buildMenuItem("acorn.life"));
		this.add(buildMenuItem("b_heptomino.life"));
		this.add(buildMenuItem("empty_board.life"));
		this.add(buildMenuItem("r_pentomino.life"));
		this.add(buildMenuItem("rabbits.life"));
		this.add(buildMenuItem("single_line_conway.life"));
	}

	private JMenuItem buildMenuItem(final String fileName) {
		JMenuItem jMenuItem = new JMenuItem();
		jMenuItem.setText(fileName);
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				InputStream inputStream = this.getClass().getResourceAsStream(PRESETS_FOLDER + fileName);
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TEMP_LIFE_PRESET));
					int read = 0;
					byte[] bytes = new byte[1024];
					while ((read = inputStream.read(bytes)) != -1) {
						fileOutputStream.write(bytes, 0, read);
					}
					fileOutputStream.close();
					File tempFile = new File(TEMP_LIFE_PRESET);
					Main.openFile(new File(TEMP_LIFE_PRESET));
					tempFile.delete();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return jMenuItem;
	}
}
