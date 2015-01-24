/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

public class Board {

	private Cell[][] board;
	private int rows, cols, countSurroundingLiveCells = 0, countBoardLiveCells = 0;

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
		return board[i][j].getCellState();
	}

	public Cell getCellAt(int i, int j) {
		// If out of bounds below
		if (i == -1) {
			return new Cell(false);
		}
		if (j == -1) {
			return new Cell(false);
		}
		// If out of bounds above
		if (i == rows) {
			return new Cell(false);
		}
		if (j == cols) {
			return new Cell(false);
		}
		return board[i][j];
	}

	public int countSurroungingLiveCells(int i, int j) {
		countSurroundingLiveCells = 0;
		// Rotating clockwise
		if (getCellAt(i + 1, j - 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i + 1, j).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i + 1, j + 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i, j + 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j + 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j - 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i, j - 1).getCellState()) {
			countSurroundingLiveCells++;
		}
		return countSurroundingLiveCells;
	}

	public void printBoard() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print("(" + i + "," + j + "): " + getCellAt(i, j).getCellState() + "\t");
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
	
	public Board iterateBoard(Board oldBoard) {
		Board newBoard = oldBoard;
		for (int i = 0; i < oldBoard.getBoardRows(); i++) {
			for (int j = 0; j < oldBoard.getBoardCols(); j++) {
				// Cell dies
				if ((oldBoard.getCellAt(i, j).getCellState())
						&& (oldBoard.countSurroungingLiveCells(i, j) < 2 || oldBoard.countSurroungingLiveCells(i, j) > 3)) {
					newBoard.getCellAt(i, j).setCellDead();
				}
				// Cell becomes alive
				if (!oldBoard.getCellAt(i, j).getCellState() && oldBoard.countSurroungingLiveCells(i, j) == 3) {
					newBoard.getCellAt(i, j).setCellAlive();
				}
			}
		}
		countLiveCells(newBoard);
		return newBoard;
	}
	
	private void countLiveCells(Board board) {
		countBoardLiveCells = 0;
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
				if (board.getCellAt(i, j).getCellState()) {
					countBoardLiveCells++;
				}
			}
		}
	}
	
	public int getBoardLiveCells() {
		return countBoardLiveCells;
	}
}
