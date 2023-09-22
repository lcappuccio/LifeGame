package org.systemexception.lifegame.gui;

import org.junit.Test;
import org.systemexception.lifegame.model.Board;

import static org.junit.Assert.*;

public class GridGuiTest {

	private final int cellSize = 10, gridRows = 10, gridCols = 5;
	private final GridGui sut = new GridGui(cellSize, gridRows, gridCols, "B & W");

	@Test
	public void testGridExists() {
		assertNotNull(sut);
	}

	@Test
	public void testGridHasBoard() {
		assertNotNull(sut.getBoard());
	}

	@Test
	public void testGridBoardHasAttributes() {
		Board board = sut.getBoard();
		assertTrue(board.getBoardCols() > 0);
		assertTrue(board.getBoardRows() > 0);
	}

	@Test
	public void testChangeCellSize() {
		int innerCellSize = sut.getCellSize();
		GridGui innerSut = new GridGui(5, gridRows, gridCols, "B & W");
		int cellSizeNew = innerSut.getCellSize();
		assertNotEquals(innerCellSize, cellSizeNew);
	}

}
