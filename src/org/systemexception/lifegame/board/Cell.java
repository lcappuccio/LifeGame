/**
 * @author leo
 * Nov 9, 2014 12:39:50 PM
 */
package org.systemexception.lifegame.board;

public class Cell {
	
	private boolean isAlive;
	
	public Cell() {
		isAlive = false;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public boolean isDead() {
		return !isAlive;
	}
	
	public void setCellAlive(boolean cellState) {
		isAlive = cellState;
	}
}
