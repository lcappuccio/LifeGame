/**
 * @author leo
 * Nov 9, 2014 12:39:50 PM
 */
package org.systemexception.lifegame.model;

import java.util.Random;

public class Cell {

	private boolean isAlive;

	public Cell() {
		Random rnd = new Random();
		if (rnd.nextInt(100) > 94) {
			isAlive = true;
		} else {
			isAlive = false;
		}
//		isAlive = rnd.nextBoolean();
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
