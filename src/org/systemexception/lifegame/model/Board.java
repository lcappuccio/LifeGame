/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

import org.systemexception.lifegame.gui.Preferences;

public class Board {

	private Cell[][] board, boardIteration;
	private int rows, cols, countSurroundingLiveCells = 0, liveCellCounter = 0;

	public Cell[][] getBoard() {
		return board;
	}

	public Board(int rows, int cols) {
		board = new Cell[rows][cols];
		this.rows = rows;
		this.cols = cols;
		generateBoard(rows, cols);
	}

	public void iterateBoard() {
		if (Preferences.getLifeRules().equals("Conway's Life")) {
			iterateBoardConway();
		}
		if (Preferences.getLifeRules().equals("DryLife")) {
			iterateBoardDryLife();
		}
		if (Preferences.getLifeRules().equals("HighLife")) {
			iterateBoardHighLife();
		}
		if (Preferences.getLifeRules().equals("Live Free or Die")) {
			iterateBoardLiveFreeOrDie();
		}
		if (Preferences.getLifeRules().equals("Maze")) {
			iterateBoardMaze();
		}
		if (Preferences.getLifeRules().equals("Serviettes")) {
			iterateBoardServiettes();
		}
		if (Preferences.getLifeRules().equals("Coral")) {
			iterateBoardCoral();
		}
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

	/**
	 * Conway (23/3): Any alive cell with less than 2 and more than 3 alive
	 * cells around dies. Any dead cell with exactly 3 alive neighbors comes to
	 * life.
	 */
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
				if (countSurroungingLiveCells(i, j) == 3 && !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * HighLife variation (23/36): Any alive cell with less than 2 and more than
	 * 3 alive cells around dies. Any cell with exactly 3 or 6 alive neighbors
	 * becomes alive.
	 */
	public void iterateBoardHighLife() {
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
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 6)
						&& !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * Live Free or Die (0/2): Any alive cells with alive neighbors dies. A dead
	 * cell is born if it has exactly 2 alive neighbors.
	 */
	public void iterateBoardLiveFreeOrDie() {
		liveCellCounter = 0;
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
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * DryLife (23/37): Any alive cell with less than 2 and more than 3 alive
	 * cells around dies. Any cell with exactly 3 or 7 alive neighbors becomes
	 * alive.
	 */
	public void iterateBoardDryLife() {
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
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 7)
						&& !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * Maze (12345/3): Any alive cell with less than 1 and more than 5 alive
	 * neighbours dies. Any dead cell with exactly 3 alive neighbors becomes
	 * alive.
	 */
	public void iterateBoardMaze() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < 1 || countSurroungingLiveCells(i, j) > 5)
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
	}

	/**
	 * Serviettes (/234): Every alive cell dies. Any dead cell with 2,3 or 4
	 * alive neighbours becomes alive.
	 */
	public void iterateBoardServiettes() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if (board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) >= 2 && countSurroungingLiveCells(i, j) <= 4)
						&& (!board[i][j].getCellState())) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * Coral (45678/3): Every alive cell with 4 to 8 alive neighbours dies.
	 * Every dead cell with exactly 3 neighbours becomes alvie.
	 */
	public void iterateBoardCoral() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) >= 4 && countSurroungingLiveCells(i, j) <= 8)
						&& board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 6)
						&& !board[i][j].getCellState()) {
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
