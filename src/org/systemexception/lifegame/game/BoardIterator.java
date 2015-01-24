/**
 * @author leo
 * Nov 15, 2014 2:43:38 PM
 */
package org.systemexception.lifegame.game;

import javax.swing.JComponent;

import org.systemexception.lifegame.model.Board;

public class BoardIterator extends JComponent {

	private static final long serialVersionUID = -136951560110034304L;
	private Board newBoard;

	public Board iterateBoard(Board oldBoard) {
		newBoard = oldBoard;
		for (int i = 0; i < oldBoard.getBoardRows(); i++) {
			for (int j = 0; j < oldBoard.getBoardCols(); j++) {
				// Cell dies
				if ((oldBoard.getCellAt(i, j).isAlive())
						&& (oldBoard.countSurroungingLiveCells(i, j) == 2 || oldBoard.countSurroungingLiveCells(i, j) == 3)) {
					newBoard.getCellAt(i, j).setCellAlive();
				} else  {
					newBoard.getCellAt(i, j).setCellDead();
				}
				if (oldBoard.getCellAt(i, j).isDead() && oldBoard.countSurroungingLiveCells(i, j) == 3) {
					// Cell becomes alive
					newBoard.getCellAt(i, j).setCellAlive();
				}
			}
		}
		return newBoard;
	}
}
