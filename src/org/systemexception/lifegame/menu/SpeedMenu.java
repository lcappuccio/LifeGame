/**
 * @author leo
 * Jan 26, 2015 9:35:36 PM
 */
package org.systemexception.lifegame.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.systemexception.lifegame.gui.Main;

import com.sun.glass.events.KeyEvent;

public class SpeedMenu extends JMenu {

	private static final long serialVersionUID = -7338470638776832322L;
	private JMenuItem speedMenuItem;
	private int[] simulationSpeeds = { 500, 360, 210, 60, 10 };
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

	private JMenuItem speedMenu(String speedName) {
		speedMenuItem = new JMenuItem(speedName);
		speedMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1 + counter, Main.metaKey));
		speedMenuItem.addActionListener(new ActionListener() {
			// About window
			public void actionPerformed(ActionEvent e) {
				getSimulationSpeed(counter);
				System.out.println(getSimulationSpeed(counter));
			}
		});
		counter++;
		return speedMenuItem;
	}

	public int getSimulationSpeed(int counter) {
		return simulationSpeeds[counter];
	}
}
