/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

public class Board {

	private Cell[][] board;
	private int rows, cols, countLiveCells = 0;;

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
		// TODO check this piece of code, it's not rotating ok
		// If out of bounds below
		if (i == -1) {
			i = rows - 1;
		}
		if (j == -1) {
			j = cols - 1;
		}
		// If out of bounds above
		if (i == rows) {
			i = 0;
		}
		if (j == cols) {
			j = 0;
		}
		return board[i][j];
	}

	public int countSurroungingLiveCells(int i, int j) {
		countLiveCells = 0;
		// Rotating clockwise
		if (getCellAt(i + 1, j + 1).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i + 1, j).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i + 1, j + 1).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i, j + 1).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i - 1, j + 1).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i - 1, j).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i - 1, j - 1).isAlive()) {
			countLiveCells++;
		}
		if (getCellAt(i, j - 1).isAlive()) {
			countLiveCells++;
		}
		return countLiveCells;
	}

	public void printBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("(" + i + "," + j + "): " + getCellAt(i, j).isAlive() + "\t");
			}
			System.out.print("\n");
		}
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
