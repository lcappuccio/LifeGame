/**
 * @author leo
 * Jan 30, 2015 9:47:20 PM
 */
package org.systemexception.lifegame.enums;

public enum SavedBoardProperties {

	AUTOMATA("automata"),
	CELLSIZE("cellSize"),
	COLS("cols"),
	ROWS("rows"),
	THEME("theme");

	private final String property;

	SavedBoardProperties(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return property;
	}
}
