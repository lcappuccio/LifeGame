/**
 * @author leo
 * Nov 15, 2014 2:43:38 PM
 */
package org.systemexception.lifegame.game;

import org.systemexception.lifegame.model.Board;

public class BoardIterator {

	private Board newBoard, oldBoard;

	public BoardIterator(Board oldBoard) {
		this.oldBoard = oldBoard;
	}
<<<<<<< HEAD
=======

	public int FateOfCellAt(int i, int j) {
		return oldBoard.countSurroungingLiveCells(i, j);
	}

>>>>>>> develop
}
