/**
 * @author leo
 * Nov 9, 2014 1:27:42 PM
 */
package org.systemexception.lifegame.model;

import org.junit.Test;
import org.systemexception.lifegame.enums.Automata;
import org.systemexception.lifegame.gui.PreferencesGui;

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
    public void testConwayVerticalOscillator() {
        sut = new Board(3, 3);
        sut.setCellAt(0, 0, false);
        sut.setCellAt(0, 1, true);
        sut.setCellAt(0, 2, false);
        sut.setCellAt(1, 0, false);
        sut.setCellAt(1, 1, true);
        sut.setCellAt(1, 2, false);
        sut.setCellAt(2, 0, false);
        sut.setCellAt(2, 1, true);
        sut.setCellAt(2, 2, false);
        PreferencesGui.setLifeAutomata(Automata.CONWAY);
        sut.iterateBoard();
        assertTrue(sut.getCellAt(1, 0));
        assertTrue(sut.getCellAt(1, 1));
        assertTrue(sut.getCellAt(1, 2));
    }


    @Test
    public void testAssimilationIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.ASSIMILATION);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testDryLifeIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.DRYLIFE);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testHighLifeIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.HIGHLIFE);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testLiveFreeOrDieIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.LIVEFREEORDIE);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testCoralIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.CORAL);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testMazeIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.MAZE);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testMoveIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.MOVE);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    @Test
    public void testServiettesIteration() {
        sut = new Board(100, 100);
        int cellsCounterBefore = sut.getCellAliveCount();
        PreferencesGui.setLifeAutomata(Automata.SERVIETTES);
        sut.iterateBoard();
        int cellsCounterAfter = sut.getCellAliveCount();
        assertFalse(compareCounters(cellsCounterAfter, cellsCounterBefore));
    }

    private boolean compareCounters(final int aliveCellsBefore, final int aliveCellsAfter) {
        return aliveCellsAfter == aliveCellsBefore;
    }
}
