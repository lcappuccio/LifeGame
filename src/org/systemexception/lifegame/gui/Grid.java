/**
 * @author leo
 * Nov 9, 2014 1:06:14 PM
 */
package org.systemexception.lifegame.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import org.systemexception.lifegame.model.Board;

public class Grid extends JComponent {

	private static final long serialVersionUID = 7546830315256844429L;
	private Board board;
	private int cellSize, gridRows, gridCols, totalLiveCells;

	public Grid(int cellSize, int gridRows, int gridCols) {
		this.cellSize = cellSize;
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		this.board = new Board(gridRows, gridCols);
	}
	
	public void setCellValue(int x) {
		cellSize = x;
	}
	
	public void resetBoard() {
		this.board = new Board(gridRows, gridCols);
		totalLiveCells = board.getLiveCellCount();
		System.out.println(totalLiveCells);
	}

	public Board getBoard() {
		return board;
	}
	
	public int getCellSize() {
		return cellSize;
	}
	
	public void iterateBoard() {
		this.board = board.iterateBoard(board);
		totalLiveCells = board.getLiveCellCount();
	}
	
	public int getTotalLiveCells() {
		return totalLiveCells;
	}

	public void paintComponent(Graphics g) {
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
				g.setColor(board.getCellAt(i, j).getCellState() ? Color.DARK_GRAY : Color.WHITE);
				g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
				g.setColor(Color.LIGHT_GRAY);
				g.drawRect(cellSize * i, cellSize * j, cellSize, cellSize);
			}
		}
		totalLiveCells = board.getLiveCellCount();
	}
}
