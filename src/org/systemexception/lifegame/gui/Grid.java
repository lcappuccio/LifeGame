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
	private int cellSize, gridRows, gridCols, totalLiveCells;
	private Board board;
	private Color colorDark = Color.DARK_GRAY, colorLight = Color.WHITE, colorRect = Color.LIGHT_GRAY;

	public Grid(int cellSize, int gridRows, int gridCols, String colourTheme) {
		this.cellSize = cellSize;
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		this.board = new Board(gridRows, gridCols);
		totalLiveCells = board.getLiveCellCount();
		setColours(colourTheme);
	}

	public void setCellValue(int x) {
		cellSize = x;
	}

	public void resetBoard() {
		this.board = new Board(gridRows, gridCols);
		totalLiveCells = board.getLiveCellCount();
	}

	public Board getBoard() {
		return board;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void iterateBoard() {
		board.iterateBoardConway();
		// board.iterateBoardHighLife();
		// board.iterateLiveFreeOrDie();
		totalLiveCells = board.getLiveCellCount();
		this.repaint();
	}

	public int getTotalLiveCells() {
		return totalLiveCells;
	}

	/**
	 * Colours from: http://www.flatuicolorpicker.com
	 * 
	 * @param colourTheme
	 */
	private void setColours(String colourTheme) {
		if (colourTheme.equals("B & W")) {
			colorDark = hex2Rgb("#ECECEC");
			colorLight = Color.DARK_GRAY;
			colorRect = Color.BLACK;
		}
		if (colourTheme.equals("Inverse")) {
			colorDark = Color.DARK_GRAY;
			colorLight = hex2Rgb("#ECECEC");
			colorRect = Color.LIGHT_GRAY;
		}
		if (colourTheme.equals("Blue")) {
			colorDark = hex2Rgb("#19B5FE");
			colorLight = hex2Rgb("#336E7B");
			colorRect = hex2Rgb("#446CB3");
		}
		if (colourTheme.equals("Green")) {
			colorDark = hex2Rgb("#2ECC71");
			colorLight = hex2Rgb("#1E824C");
			colorRect = hex2Rgb("#26A65B");
		}
		if (colourTheme.equals("Red")) {
			colorDark = hex2Rgb("#EF4836");
			colorLight = hex2Rgb("#96281B");
			colorRect = hex2Rgb("#CF000F");
		}
	}

	/**
	 * As seen on:
	 * http://stackoverflow.com/questions/4129666/how-to-convert-hex-
	 * to-rgb-using-java
	 * 
	 * @param colorStr
	 *            e.g. "#FFFFFF"
	 * @return
	 */
	private static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	@Override
	public void paintComponent(Graphics g) {
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
				g.setColor(board.getCellAt(i, j).getCellState() ? colorDark : colorLight);
				g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
				g.setColor(colorRect);
				g.drawRect(cellSize * i, cellSize * j, cellSize, cellSize);
			}
		}
		totalLiveCells = board.getCellAliveCount();
	}
}
