/**
 * @author leo
 * Nov 9, 2014 11:42:43 AM
 */
package org.systemexception.lifegame.model;

import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.gui.PreferencesGui;

import java.util.List;
import java.util.Random;

public class Board {

	public static final String ALIVE_CELL = "o", DEAD_CELL = ".";
	private final int rows, cols;
	private boolean[][] board, boardIteration;
	private int liveCellCounter = 0;

	public Board(int rows, int cols) {
		this.board = new boolean[rows][cols];
		this.rows = rows;
		this.cols = cols;
		generateBoard(rows, cols);
	}

	public Board(int rows, int cols, List<List<String>> savedBoard) {
		this.board = new boolean[rows][cols];
		this.rows = rows;
		this.cols = cols;
		setBoardFromSavedFile(savedBoard);
	}

	public void iterateBoard() {
		if (PreferencesGui.getLifeAutomata().equals(Automata.ASSIMILATION.toString())) {
			iterateBoardAssimilation();
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.CONWAY.toString())) {
			iterateBoardConway();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.CORAL.toString())) {
			iterateBoardCoral();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.DRYLIFE.toString())) {
			iterateBoardDryLife();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.HIGHLIFE.toString())) {
			iterateBoardHighLife();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.LIVEFREEORDIE.toString())) {
			iterateBoardLiveFreeOrDie();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.MAZE.toString())) {
			iterateBoardMaze();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.MOVE.toString())) {
			iterateBoardMove();
			return;
		}
		if (PreferencesGui.getLifeAutomata().equals(Automata.SERVIETTES.toString())) {
			iterateBoardServiettes();
		}
	}

	private void generateBoard(int rows, int cols) {
        Random random = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = random.nextInt(101) > (100 - PreferencesGui.getCellLifeProbability());
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

	public int getCellAliveCount() {
		return liveCellCounter;
	}

	public boolean getCellAt(int i, int j) {
		// If out of bounds
		if (i <= -1 || j <= -1 || i >= rows || j >= cols) {
			return false;
		} else {
			return board[i][j];
		}
	}

    public void setCellAt(int i, int j, boolean cellState) {
        board[i][j] = cellState;
    }

	private int countSurroungingLiveCells(int i, int j) {
		int countSurroundingLiveCells = 0;
		// Rotating clockwise
		if (getCellAt(i, j - 1)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i + 1, j - 1)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i + 1, j)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i + 1, j + 1)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i, j + 1)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j + 1)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j)) {
			countSurroundingLiveCells++;
		}
		if (getCellAt(i - 1, j - 1)) {
			countSurroundingLiveCells++;
		}
		return countSurroundingLiveCells;
	}

	/**
	 * Conway (23/3): Any alive cell with less than 2 and more than 3 alive
	 * cells around dies. Any dead cell with exactly 3 alive neighbors comes to
	 * life.
	 */
	private void iterateBoardConway() {
		iterateConwayOrMaze(2,3,3);
	}

	/**
	 * HighLife variation (23/36): Any alive cell with less than 2 and more than
	 * 3 alive cells around dies. Any cell with exactly 3 or 6 alive neighbors
	 * becomes alive.
	 */
	private void iterateBoardHighLife() {
		iterateHighOrDryLife(2, 3, 3, 6);
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
				if (countSurroungingLiveCells(i, j) > 0 && board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if (countSurroungingLiveCells(i, j) == 2 && !board[i][j]) {
					boardIteration[i][j] = true;
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
		iterateHighOrDryLife(2, 3, 3, 7);
	}

	/**
	 * Maze (12345/3): Any alive cell with less than 1 and more than 5 alive
	 * neighbours dies. Any dead cell with exactly 3 alive neighbors becomes
	 * alive.
	 */
	private void iterateBoardMaze() {
		iterateConwayOrMaze(1,5,3);
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
				if (board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) >= 2 && countSurroungingLiveCells(i, j) <= 4)
						&& (!board[i][j])) {
					boardIteration[i][j] = true;
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
				if (countSurroungingLiveCells(i, j) < 4 && board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3) && !board[i][j]) {
					boardIteration[i][j] = true;
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
				if (!(countSurroungingLiveCells(i, j) == 2 || (countSurroungingLiveCells(i, j) == 4 ||
						countSurroungingLiveCells(i, j) == 5)) && board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == 3 || countSurroungingLiveCells(i, j) == 6 ||
						countSurroungingLiveCells(i, j) == 8) && !board[i][j]) {
					boardIteration[i][j] = true;
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
						&& board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) >= 3 && countSurroungingLiveCells(i, j) <= 5)
						&& !board[i][j]) {
					boardIteration[i][j] = true;
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	private void updateLiveCellCounter(boolean[][] board, int i, int j) {
		if (board[i][j]) {
			liveCellCounter++;
		}
	}

	private void copyBoard() {
		boardIteration = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, boardIteration[i], 0, board[i].length);
		}
	}

	private void setBoardFromSavedFile(List<List<String>> savedBoard) {
		liveCellCounter = 0;
        boolean cellState;
		for (int i = 0; i < savedBoard.size(); i++) {
			for (int j = 0; j < savedBoard.get(i).size(); j++) {
				if (savedBoard.get(i).get(j).equals(ALIVE_CELL)) {
                    cellState = true;
					liveCellCounter++;
				} else {
                    cellState = false;
				}
				board[j][i] = cellState;
			}
		}
	}

	private void iterateHighOrDryLife(final int aliveLowerBound, final int aliveUpperBound, final int alivelowerEqual,
	                                  final int aliveUpperEqual) {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < aliveLowerBound ||
						countSurroungingLiveCells(i, j) > aliveUpperBound) && board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if ((countSurroungingLiveCells(i, j) == alivelowerEqual ||
						countSurroungingLiveCells(i, j) == aliveUpperEqual) && !board[i][j]) {
					boardIteration[i][j] = true;
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}

	private void iterateConwayOrMaze(final int aliveLowerBound, final int aliveUpperBound, final int alivelowerEqual) {
		liveCellCounter = 0;
		copyBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				// Cell dies
				if ((countSurroungingLiveCells(i, j) < aliveLowerBound ||
						countSurroungingLiveCells(i, j) > aliveUpperBound) && board[i][j]) {
					boardIteration[i][j] = false;
				}
				// Cell becomes alive
				if (countSurroungingLiveCells(i, j) == alivelowerEqual && !board[i][j]) {
					boardIteration[i][j] = true;
				}
				updateLiveCellCounter(boardIteration, i, j);
			}
		}
		this.board = boardIteration;
	}
}
