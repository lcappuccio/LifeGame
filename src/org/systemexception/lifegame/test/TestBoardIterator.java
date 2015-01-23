/**
 * @author leo
 * Nov 15, 2014 2:48:09 PM
 */
package org.systemexception.lifegame.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.systemexception.lifegame.game.BoardIterator;
import org.systemexception.lifegame.model.Board;

public class TestBoardIterator {

	private BoardIterator sut;
	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board(3, 3);
		sut = new BoardIterator();
		System.out.println("Board at start");
		board.printBoard();
	}

	@Test
	@Ignore
	public void iterateBoard() {
		board = sut.iterateBoard(board);
		System.out.println("Board at iteration");
		board.printBoard();
	}
	
	@Test
	public void test10Iterations() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Board at iteration " + i);
			board = sut.iterateBoard(board);
			board.printBoard();
		}
	}
}
