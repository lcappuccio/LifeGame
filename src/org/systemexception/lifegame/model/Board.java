/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

public class Board {

	private Cell[][] board;
	private int rows, cols;

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}

	public int getBoardRows() {
		return rows;
	}

	public int getBoardCols() {
		return cols;
	}

	public boolean getCellIsAlive(int i, int j) {
		return board[i][j].isAlive();
	}

	/**
	 * We're representing a borderless board, so the neighbor of the first
	 * column and row is the other side of the board
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getCellAt(int i, int j) {
		// If out of bounds below
		if (i < 0) {
			i = rows - 1;
		}
		if (j < 0) {
			j = cols - 1;
		}
		// If out of bounds above
		if (i > rows) {
			i = 0;
		}
		if (j > cols) {
			j = 0;
		}
		return board[i][j];
	}

	public int countSurroungingLiveCells(int i, int j) {
		int countLiveCells = 0;
		return countLiveCells;
	}

	public Board(int rows, int cols) {
		board = new Cell[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = new Cell();
				board[i][j] = cell;
			}
		}
	}
}
