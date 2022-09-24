package org.systemexception.lifegame.gui;

import org.junit.Test;
import org.systemexception.lifegame.model.Board;
import org.systemexception.lifegame.model.Cell;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
	public void testGridBoardHasCells() {
		Board board = sut.getBoard();
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
				Cell cell = board.getCellAt(i, j);
				assertNotNull(cell);
			}
		}
	}

	@Test
	public void testChangeCellSize() {
		int innerCellSize = sut.getCellSize();
		GridGui innerSut = new GridGui(5, gridRows, gridCols, "B & W");
		int cellSizeNew = innerSut.getCellSize();
		assertTrue(innerCellSize != cellSizeNew);
	}

}
