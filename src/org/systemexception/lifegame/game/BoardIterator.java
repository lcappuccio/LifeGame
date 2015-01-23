/**
 * @author leo
 * Nov 15, 2014 2:43:38 PM
 */
package org.systemexception.lifegame.game;

import org.systemexception.lifegame.model.Board;

public class BoardIterator {

	private Board newBoard, oldBoard;

	public int FateOfCellAt(int i, int j) {
		return oldBoard.countSurroungingLiveCells(i, j);
	}
	
	public Board iterateBoard(Board oldBoard) {
		this.oldBoard = oldBoard;
		newBoard = new Board(oldBoard.getBoardRows(), oldBoard.getBoardCols());
		for (int i = 0; i < oldBoard.getBoardCols(); i ++) {
			for (int j = 0; j < oldBoard.getBoardRows(); j++) {
				if (oldBoard.getCellAt(i, j).isAlive()) {
					// Cell dies
					if (oldBoard.countSurroungingLiveCells(i, j) < 2) {
						newBoard.getCellAt(i, j).setCellDead();
					}
					// Cell lives but is already alive so no changes here
//					if (board.countSurroungingLiveCells(i, j) >= 2 && board.countSurroungingLiveCells(i, j) <= 3) {
//						
//					}
					if (oldBoard.countSurroungingLiveCells(i, j) > 3) {
						newBoard.getCellAt(i, j).setCellDead();
					}
				} else {
					// Cell becomes alive
					if (oldBoard.countSurroungingLiveCells(i, j) == 3) {
						newBoard.getCellAt(i, j).setCellAlive();
					}
				}
			}
		}
		return newBoard;
	}
}
