/**
 * @author leo
 * Nov 15, 2014 2:48:09 PM
 */
package org.systemexception.lifegame.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.systemexception.lifegame.game.BoardIterator;
import org.systemexception.lifegame.model.Board;

public class TestBoardIterator {

	BoardIterator sut;

	@Before
	public void setUp() throws Exception {
		Board board = new Board(3, 3);
		sut = new BoardIterator(board);
		board.printBoard();
	}
}
