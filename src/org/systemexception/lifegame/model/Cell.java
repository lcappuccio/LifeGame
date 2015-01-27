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
		if (rnd.nextInt(100) > (100 - Preferences.getCellLifeProbability())) {
			isAlive = true;
		} else {
			isAlive = false;
		}
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
