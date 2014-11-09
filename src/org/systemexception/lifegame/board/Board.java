/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.board;

public class Board {

	private Cell[][] board;
	private int boardRows, boardCols;

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public int getBoardRows() {
		return boardRows;
	}

	public int getBoardCols() {
		return boardCols;
	}

	public boolean getCellIsAlive(int i, int j) {
		return board[i][j].isAlive();
	}

	public Cell getCellAt(int i, int j) {
		return board[i][j];
	}

	public Board(int rows, int cols) {
		board = new Cell[rows][cols];
		boardRows = rows;
		boardCols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = new Cell();
				board[i][j] = cell;
			}
		}
	}
}
