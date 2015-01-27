/**
 * @author leo
 * Nov 9, 2014 1:27:42 PM
 */
package org.systemexception.lifegame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.systemexception.lifegame.model.Board;
import org.systemexception.lifegame.model.Cell;

public class TestBoard {

	private int boardSizeX = 5, boardSizeY = 5;
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
	@Deprecated
	@Ignore
	public void boardIsRoundHorizontally() {
		// Border is no longer "round"
		Cell myCell = sut.getCellAt(2, 0);
		assertSame(myCell, sut.getCellAt(-1, 0));
	}

	@Test
	@Deprecated
	@Ignore
	public void boardIsRoundVertically() {
		// Border is no longer "round"
		Cell myCell = sut.getCellAt(2, 2);
		assertSame(myCell, sut.getCellAt(2, -1));
	}

	@Test
	@Ignore
	@SuppressWarnings("deprecation")
	public void verifySurroundingAliveCellsCount() {
		int i = 1, j = 2;
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
		int aliveCellCount = sut.countSurroungingLiveCells(i, j);
		sut.printBoard();
		System.out.println("Live cells around (" + i + "," + j + "): " + aliveCellCount);
	}

	@Test
	public void testVerticalOscillator() {
		Board sut = new Board(3, 3);
		sut.getCellAt(0, 0).setCellDead();
		sut.getCellAt(0, 1).setCellAlive();
		sut.getCellAt(0, 2).setCellDead();
		sut.getCellAt(1, 0).setCellDead();
		sut.getCellAt(1, 1).setCellAlive();
		sut.getCellAt(1, 2).setCellDead();
		sut.getCellAt(2, 0).setCellDead();
		sut.getCellAt(2, 1).setCellAlive();
		sut.getCellAt(2, 2).setCellDead();
		sut.iterateBoardConway();
		assertTrue(sut.getCellAt(1, 0).getCellState());
		assertTrue(sut.getCellAt(1, 1).getCellState());
		assertTrue(sut.getCellAt(1, 2).getCellState());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testLiveCellCount() {
		sut = new Board(100, 100);
		assertEquals(sut.getLiveCellCount(), sut.getCellAliveCount());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testLiveCellCountIteration() {
		sut = new Board(1000, 1000);
		sut.iterateBoardConway();
		assertEquals(sut.getLiveCellCount(), sut.getCellAliveCount());
	}
}
