/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

public class Board {

	private Cell[][] board, boardIteration;
	private int rows, cols, countSurroundingLiveCells = 0;
	private int rows, cols, countSurroundingLiveCells = 0, liveCellCounter = 0;

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	public Board(int rows, int cols) {
		board = new Cell[rows][cols];
		this.rows = rows;
		this.cols = cols;
		generateBoard(rows, cols);
	}

	public Board resetBoard(int rows, int cols) {
		generateBoard(rows, cols);
		return this;
	}

	private void generateBoard(int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				Cell cell = new Cell();
				board[i][j] = cell;
				updateLiveCellCounter(board, i, j);
			}
		}
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

	@Deprecated
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

	public void iterateBoardConway() {
<<<<<<< HEAD
=======
		liveCellCounter = 0;
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < 2 || countSurroungingLiveCells(i, j) > 3)
						&& board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if (countSurroungingLiveCells(i, j) == 3 && !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
<<<<<<< HEAD
	}

	public void iterateBoardHighLife() {
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < 2 || countSurroungingLiveCells(i, j) > 3)
						&& board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 6)
						&& !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
			}
		}
		this.board = boardIteration;
	}

	public void iterateLiveFreeOrDie() {
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if (countSurroungingLiveCells(i, j) > 0 && board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if (countSurroungingLiveCells(i, j) == 2 && !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
			}
		}
		this.board = boardIteration;
	}

	public int getLiveCellCount() {
		// TODO move this method somewhere else to avoid doing for cycles again!
		int liveCellCounter = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getCellState()) {
					liveCellCounter++;
				}
			}
=======
	}

	private void updateLiveCellCounter(Cell[][] board, int i, int j) {
		if (board[i][j].getCellState()) {
			liveCellCounter++;
>>>>>>> 16f6b7bbcc29b06c0a3f6a31e5b2d0d9bb37d2fc
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
}
