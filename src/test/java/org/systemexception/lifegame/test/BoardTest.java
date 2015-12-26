/**
 * @author leo
 * Nov 9, 2014 1:27:42 PM
 */
package org.systemexception.lifegame.test;

import org.junit.Test;
import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.gui.PreferencesGui;
import org.systemexception.lifegame.model.Board;

import static org.junit.Assert.*;

public class BoardTest {

	private final int boardSizeX = 5, boardSizeY = 5;
	private Board sut = new Board(boardSizeX, boardSizeY);

	@Test
	public void testBordExists() {
		assertNotNull(sut);
	}

	@Test
	public void boardHasCorrectSize() {
		assertEquals(sut.getBoardRows(), boardSizeX);
		assertEquals(sut.getBoardCols(), boardSizeY);
	}

	@Test
	public void newBoardHasCells() {
		for (int i = 0; i < sut.getBoardRows(); i++) {
			for (int j = 0; j < sut.getBoardCols(); j++) {
				assertNotNull(sut.getCellAt(i, j));
			}
		}
	}

	@Test
	public void testConwayVerticalOscillator() {
		sut = new Board(3, 3);
		sut.getCellAt(0, 0).setCellDead();
		sut.getCellAt(0, 1).setCellAlive();
		sut.getCellAt(0, 2).setCellDead();
		sut.getCellAt(1, 0).setCellDead();
		sut.getCellAt(1, 1).setCellAlive();
		sut.getCellAt(1, 2).setCellDead();
		sut.getCellAt(2, 0).setCellDead();
		sut.getCellAt(2, 1).setCellAlive();
		sut.getCellAt(2, 2).setCellDead();
		PreferencesGui.setLifeAutomata(Automata.CONWAY.toString());
		sut.iterateBoard();
		assertTrue(sut.getCellAt(1, 0).getCellState());
		assertTrue(sut.getCellAt(1, 1).getCellState());
		assertTrue(sut.getCellAt(1, 2).getCellState());
	}


	@Test
	public void testAssimilationIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.ASSIMILATION.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testDryLifeIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.DRYLIFE.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testHighLifeIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.HIGHLIFE.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testLiveFreeOrDieIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.LIVEFREEORDIE.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testCoralIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.CORAL.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testMazeIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.MAZE.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testMoveIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.MOVE.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	@Test
	public void testServiettesIteration() {
		sut = new Board(100,100);
		int cellsCounterBefore = sut.getCellAliveCount();
		PreferencesGui.setLifeAutomata(Automata.SERVIETTES.toString());
		sut.iterateBoard();
		int cellsCounterAfter = sut.getCellAliveCount();
		assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
	}

	private boolean compareCounters(final int aliveCellsBefore, final int aliveCellsAfter) {
		return aliveCellsAfter == aliveCellsBefore;
	}
}
