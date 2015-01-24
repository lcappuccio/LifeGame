package org.systemexception.lifegame.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.systemexception.lifegame.gui.Grid;
import org.systemexception.lifegame.model.Board;
import org.systemexception.lifegame.model.Cell;

public class TestGrid {

	private int cellSize = 10, gridRows = 10, gridCols = 5;
	private Grid sut = new Grid(cellSize, gridRows, gridCols);
	
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
				assertNotNull(cell.getCellState());
			}
		}
	}
	
	@Test
	public void testChangeCellSize() {
		int cellSize = sut.getCellSize();
		Grid sut = new Grid(5, gridRows, gridCols);
		int cellSizeNew = sut.getCellSize();
		assertTrue(cellSize != cellSizeNew);
	}
	
	
}
