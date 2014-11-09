package org.systemexception.lifegame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.systemexception.lifegame.board.Board;
import org.systemexception.lifegame.board.Cell;

public class BoardTests {
	
	private int boardSizeX = 10, boardSizeY = 5;
	Board sut = new Board(boardSizeX, boardSizeY);

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
	public void newBoardIsZeroed() {
		Cell[][] board = sut.getBoard();
		for (int i = 0; i < sut.getBoardRows(); i++) {
			for (int j = 0; j < sut.getBoardCols(); j++) {
				assertTrue(!board[i][j].isAlive());
			}
		}
	}
}
