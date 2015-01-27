/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

public class Board {

	private Cell[][] board, boardIteration;
	private int rows, cols, countSurroundingLiveCells = 0, liveCellCounter = 0;

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

	public int getCellAliveCount() {
		return liveCellCounter;
	}

	public Cell getCellAt(int i, int j) {
		// If out of bounds below
		if (i <= -1) {
			return new Cell(false);
		}
		if (j <= -1) {
			return new Cell(false);
		}
		// If out of bounds above
		if (i >= rows) {
			return new Cell(false);
		}
		if (j >= cols) {
			return new Cell(false);
		}
		return board[i][j];
	}

	public int countSurroungingLiveCells(int i, int j) {
		countSurroundingLiveCells = 0;
		// Rotating clockwise
		if (getCellAt(i, j - 1).getCellState()) {
			countSurroundingLiveCells++;
		}
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
				updateLiveCellCounter(board, i, j);
			}
		}
	}

	public void iterateBoardConway() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < 2 || countSurroungingLiveCells(i, j) > 3)
						&& board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if (!board[i][j].getCellState() && countSurroungingLiveCells(i, j) == 3) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;

	}

	private void updateLiveCellCounter(Cell[][] board, int i, int j) {
		if (board[i][j].getCellState()) {
			liveCellCounter++;
		}
	}

	@Deprecated
	public int getLiveCellCount() {
		int liveCellCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getCellState()) {
					liveCellCounter++;
				}
			}
		}
		return liveCellCounter;
	}

	public void copyBoard() {
		boardIteration = new Cell[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Cell cell = new Cell(board[i][j].getCellState());
				boardIteration[i][j] = cell;
			}
		}
	}
}
