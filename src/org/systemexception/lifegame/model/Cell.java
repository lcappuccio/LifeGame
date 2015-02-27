/**
 * @author leo
 * Nov 9, 2014 12:39:50 PM
 */
package org.systemexception.lifegame.model;

import java.util.Random;

import org.systemexception.lifegame.gui.Preferences;

public class Cell {

	private boolean isAlive;

	public Cell() {
		Random rnd = new Random();
		isAlive = rnd.nextInt(101) > (100 - Preferences.getCellLifeProbability());
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
