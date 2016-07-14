/**
 * @author leo
 * Nov 9, 2014 12:39:50 PM
 */
package org.systemexception.lifegame.model;

import org.systemexception.lifegame.gui.PreferencesGui;

import java.util.Random;

public class Cell {

	private boolean isAlive;

	public Cell() {
		Random rnd = new Random();
		isAlive = rnd.nextInt(101) > (100 - PreferencesGui.getCellLifeProbability());
	}

	public Cell(Boolean state) {
		this.isAlive = state;
	}

	public boolean getCellState() {
		return isAlive;
	}

	public void setCellAlive() {
		isAlive = true;
	}

	public void setCellDead() {
		isAlive = false;
	}
}
