/**
 * @author leo
 * Jan 26, 2015 9:35:36 PM
 */
package org.systemexception.lifegame.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.systemexception.lifegame.gui.Main;

import com.sun.glass.events.KeyEvent;

public class SpeedMenu extends JMenu {

	private static final long serialVersionUID = -7338470638776832322L;
	private static final int CHEETAH_SPEED = 500, JACKRABBIT_SPEED = 360, HORSE_SPEED = 210, LLAMA_SPEED = 60, TURTLE_SPEED = 10;
	private static final int[] simulationSpeeds = {CHEETAH_SPEED, JACKRABBIT_SPEED, HORSE_SPEED, LLAMA_SPEED,
			TURTLE_SPEED };
	private Hashtable<String, Integer> speedTable = new Hashtable<String, Integer>();
	private int counter = 0;

	public SpeedMenu() {
		this.setFont(Main.MENU_FONT);
		this.setText("Speed");
		this.add(speedMenu("Turle"));
		this.add(speedMenu("Llama"));
		this.add(speedMenu("Horse"));
		this.add(speedMenu("Jackrabbit"));
		this.add(speedMenu("Cheetah"));
	}

	private JMenuItem speedMenu(String speedMenu) {
		JMenuItem speedMenuItem = new JMenuItem(speedMenu);
		speedTable.put(speedMenu, simulationSpeeds[counter]);
		speedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1 + counter, Main.metaKey));
		speedMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getSpeed(speedMenuItem.getText());
			}
		});
		counter++;
		return speedMenuItem;
	}

	private void getSpeed(String speedDescription) {
		if (Main.gameTimer != null && Main.gameTimer.isRunning()) {
			Main.gameTimer.setDelay(speedTable.get(speedDescription));
		}
	}
}
