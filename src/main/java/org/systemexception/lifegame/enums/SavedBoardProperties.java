/**
 * @author leo
 * Jan 30, 2015 9:47:20 PM
 */
package org.systemexception.lifegame.enums;

public enum SavedBoardProperties {

	COLS("cols"), ROWS("rows"), CELLSIZE("cellSize"), AUTOMATA("automata"), THEME("theme");

	private final String property;

	SavedBoardProperties(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return property;
	}
}
