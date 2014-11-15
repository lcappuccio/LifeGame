/**
 * @author leo
 * Nov 15, 2014 2:48:09 PM
 */
package org.systemexception.lifegame.test;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.lifegame.model.Board;

public class TestBoardIterator {
	
	Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(3, 3);
	}
	
	@Test
	public void cellWithLessThanTwoCellsDies() {
		// Find cell with less than two alive cells around
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
			}
		}
	}
}
