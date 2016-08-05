/**
 * @author leo
 * Aug 28, 2015 13:09:09 PM
 */
package org.systemexception.lifegame.enums;


public enum BoardSizes {

	SMALL("Small"),
	MEDIUM("Medium"),
	LARGE("Large");

	private final String boardSize;

	BoardSizes(final String boardSize) {
		this.boardSize = boardSize;
	}

	@Override
	public String toString() {
		return boardSize;
	}
}
