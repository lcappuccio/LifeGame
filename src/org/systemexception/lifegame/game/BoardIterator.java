/**
 * @author leo
 * Nov 15, 2014 2:43:38 PM
 */
package org.systemexception.lifegame.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import org.systemexception.lifegame.model.Board;

public class BoardIterator extends JComponent {

	private static final long serialVersionUID = -136951560110034304L;
	private Board newBoard, oldBoard;

	public int FateOfCellAt(int i, int j) {
		return oldBoard.countSurroungingLiveCells(i, j);
	}
	
	public Board iterateBoard(Board oldBoard) {
		newBoard = oldBoard;
		for (int i = 0; i < oldBoard.getBoardRows(); i ++) {
			for (int j = 0; j < oldBoard.getBoardCols(); j++) {
				if (oldBoard.getCellAt(i, j).isAlive()) {
					// Cell dies
					if (oldBoard.countSurroungingLiveCells(i, j) < 2) {
//						System.out.println("BING " + i + "," + j);
						newBoard.getCellAt(i, j).setCellDead();
					}
					// Cell lives but is already alive so no changes here
//					if (board.countSurroungingLiveCells(i, j) >= 2 && board.countSurroungingLiveCells(i, j) <= 3) {
//						
//					}
					if (oldBoard.countSurroungingLiveCells(i, j) >= 3) {
//						System.out.println("BONG " + i + "," + j);
						newBoard.getCellAt(i, j).setCellDead();
					}
				} else {
					// Cell becomes alive
					if (oldBoard.countSurroungingLiveCells(i, j) == 3) {
//						System.out.println("BANG " + i + "," + j);
						newBoard.getCellAt(i, j).setCellAlive();
					}
				}
			}
		}
		return newBoard;
	}
	
	private void resetBoard() {
		for (int i = 0; i < newBoard.getBoardRows(); i++) {
			for (int j = 0; j < newBoard.getBoardCols(); j++) {
				newBoard.getCellAt(i, j).setCellDead();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		for (int i = 0; i < newBoard.getBoardRows(); i++) {
			for (int j = 0; j < newBoard.getBoardCols(); j++) {
				g.setColor(newBoard.getCellAt(i, j).isAlive() ? Color.DARK_GRAY : Color.WHITE);
				g.fillRect(5 * i, 5 * j, 5, 10);
			}
		}
	}
}
