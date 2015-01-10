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
		isAlive = rnd.nextBoolean();
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDead() {
		return !isAlive;
	}

	public void setCellAlive() {
		isAlive = true;
	}
	
	public void setCellDead() {
		isAlive = false;
	}
}
