package org.systemexception.lifegame.menu;

import org.apache.commons.io.IOUtils;
import org.systemexception.lifegame.gui.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author leo
 * @date 22/09/15 23:01
 */
public class PresetsMenu extends JMenu {

	private static final String PRESETS_FOLDER = "presets" + File.separator;

	public PresetsMenu() {
		this.setFont(Main.MENU_FONT);
		this.setText("Presets");
		try {
			List<String> lifePresets = scanLifePresets();
			for (String lifePreset : lifePresets) {
				this.add(buildMenuItem(lifePreset));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> scanLifePresets() throws IOException {
		return IOUtils.readLines(PresetsMenu.class.getClassLoader()
				.getResourceAsStream(PRESETS_FOLDER), String.valueOf(Charset.defaultCharset()));
	}

	private JMenuItem buildMenuItem(String fileName) {
		JMenuItem jMenuItem = new JMenuItem();
		jMenuItem.setText(fileName);
		jMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				URL url = ClassLoader.getSystemResource("presets" + File.separator + fileName);
				try {
					Main.openFile(new File(url.toURI()));
				} catch (URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});
		return jMenuItem;
	}
}
