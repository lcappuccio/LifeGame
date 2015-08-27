/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.gui.Preferences;

import java.util.ArrayList;

public class Board {

	private Cell[][] board, boardIteration;
	private final int rows, cols;
	private int countSurroundingLiveCells = 0, liveCellCounter = 0;

	public Cell[][] getBoard() {
		return board;
	}

	public Board(int rows, int cols) {
		this.board = new Cell[rows][cols];
		this.rows = rows;
		this.cols = cols;
		generateBoard(rows, cols);
	}

	public Board(int rows, int cols, ArrayList<ArrayList<String>> savedBoard, String automata) {
		this.board = new Cell[rows][cols];
		this.rows = rows;
		this.cols = cols;
		setBoardFromSavedFile(savedBoard);
	}

	public void iterateBoard() {
		if (Preferences.getLifeAutomata().equals(Automata.CONWAY.toString())) {
			iterateBoardConway();
		}
		if (Preferences.getLifeAutomata().equals(Automata.DRYLIFE.toString())) {
			iterateBoardDryLife();
		}
		if (Preferences.getLifeAutomata().equals(Automata.HIGHLIFE.toString())) {
			iterateBoardHighLife();
		}
		if (Preferences.getLifeAutomata().equals(Automata.LIVEFREEORDIE.toString())) {
			iterateBoardLiveFreeOrDie();
		}
		if (Preferences.getLifeAutomata().equals(Automata.MAZE.toString())) {
			iterateBoardMaze();
		}
		if (Preferences.getLifeAutomata().equals(Automata.SERVIETTES.toString())) {
			iterateBoardServiettes();
		}
		if (Preferences.getLifeAutomata().equals(Automata.CORAL.toString())) {
			iterateBoardCoral();
		}
		if (Preferences.getLifeAutomata().equals(Automata.MOVE.toString())) {
			iterateBoardMove();
		}
		if (Preferences.getLifeAutomata().equals(Automata.ASSIMILATION.toString())) {
			iterateBoardAssimilation();
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
	private void iterateBoardHighLife() {
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
	private void iterateBoardLiveFreeOrDie() {
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
	private void iterateBoardDryLife() {
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
	private void iterateBoardMaze() {
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
	private void iterateBoardServiettes() {
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
	 * Coral (45678/3): Every alive cell with 4 to 8 alive neighbours survives.
	 * Every dead cell with exactly 3 neighbours becomes alvie.
	 */
	private void iterateBoardCoral() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if (countSurroungingLiveCells(i, j) < 4 && board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3) && !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * Move (245/368): Every alive cell with 2,4 or 5 survives. Dead cells with
	 * 3, 6 or 8 neighbours becomes alive.
	 */
	private void iterateBoardMove() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if (!(countSurroungingLiveCells(i, j) == 2 || (countSurroungingLiveCells(i, j) == 4 || countSurroungingLiveCells(
						i, j) == 5)) && board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 6 || countSurroungingLiveCells(
						i, j) == 8) && !board[i][j].getCellState()) {
					boardIteration[i][j].setCellAlive();
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	/**
	 * Assimilation (4567/345): Any live cell with 4 to 7 alive neighbours
	 * survives. Any dead cell with 3 to 5 live neighbours comes alive.
	 */
	private void iterateBoardAssimilation() {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if (!(countSurroungingLiveCells(i, j) >= 4 && countSurroungingLiveCells(i, j) <= 7)
						&& board[i][j].getCellState()) {
					boardIteration[i][j].setCellDead();
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) >= 3 && countSurroungingLiveCells(i, j) <= 5)
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

	private void copyBoard() {
		boardIteration = new Cell[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				Cell cell = new Cell(board[i][j].getCellState());
				boardIteration[i][j] = cell;
			}
		}
	}

	private void setBoardFromSavedFile(ArrayList<ArrayList<String>> savedBoard) {
		liveCellCounter = 0;
		for (int i = 0; i < savedBoard.size(); i++) {
			for (int j = 0; j < savedBoard.get(i).size(); j++) {
				Cell cell = new Cell();
				if (savedBoard.get(i).get(j).equals("o")) {
					cell.setCellAlive();
					liveCellCounter++;
				} else {
					cell.setCellDead();
				}
				board[j][i] = cell;
			}
		}
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

	@Deprecated
	public int getLiveCellCount() {
		int testCellCounter = 0;
		for (Cell[] boardRow : board) {
			for (Cell boardRowCell : boardRow) {
				if (boardRowCell.getCellState()) {
					testCellCounter++;
				}
			}
		}
		return testCellCounter;
	}
}
