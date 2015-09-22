package org.systemexception.lifegame.menu;

import org.apache.commons.io.IOUtils;
import org.systemexception.lifegame.gui.Main;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author leo
 * @date 22/09/15 23:01
 */
public class PresetsMenu extends JMenu {

	public PresetsMenu() {
		this.setFont(Main.MENU_FONT);
		UIManager.put("FileChooser.readOnly", Boolean.FALSE);
		this.setText("Presets");
		try {
			List<String> lifePresets = scanLifePresets();
			for(String lifePreset: lifePresets) {
				this.add(lifePreset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> scanLifePresets() throws IOException {
		List<String> files = IOUtils.readLines(PresetsMenu.class.getClassLoader()
				.getResourceAsStream("presets/"), String.valueOf(Charset.defaultCharset()));
		System.out.println(files);
		return files;
	}
}
