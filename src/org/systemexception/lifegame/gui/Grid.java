/**
 * @author leo
 * Nov 9, 2014 1:06:14 PM
 */
package org.systemexception.lifegame.gui;

import org.systemexception.lifegame.enums.Themes;
import org.systemexception.lifegame.model.Board;
import org.systemexception.lifegame.model.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Grid extends JComponent {

	private int cellSize, totalLiveCells;
	private final int gridRows, gridCols;
	private Board board;
	private Color colorDark = Color.DARK_GRAY, colorLight = Color.WHITE, colorRect = Color.LIGHT_GRAY;

	public Grid(final int cellSize, int gridRows, int gridCols, String colourTheme) {
		this.cellSize = cellSize;
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		this.board = new Board(gridRows, gridCols);
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Cell cell = board.getCellAt(e.getX() / cellSize, e.getY() / cellSize);
				if (cell.getCellState()) {
					cell.setCellDead();
				} else {
					cell.setCellAlive();
				}
				getParent().repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
		totalLiveCells = board.getCellAliveCount();
		setColours(colourTheme);
	}

	public Grid(int cellSize, int gridRows, int gridCols, ArrayList<ArrayList<String>> savedBoard, String automata,
	            String colourTheme) {
		this.cellSize = cellSize;
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		this.board = new Board(gridRows, gridCols, savedBoard, automata);
		totalLiveCells = board.getCellAliveCount();
		setColours(colourTheme);
		this.paint(getGraphics());
	}

	public void setCellValue(int x) {
		cellSize = x;
	}

	public void resetBoard() {
		this.board = new Board(gridRows, gridCols);
		totalLiveCells = board.getCellAliveCount();
	}

	public Board getBoard() {
		return board;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void iterateBoard() {
		board.iterateBoard();
		totalLiveCells = board.getCellAliveCount();
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
		if (colourTheme.equals(Themes.BW.toString())) {
			colorDark = hex2Rgb("#ECECEC");
			colorLight = Color.DARK_GRAY;
			colorRect = Color.BLACK;
		}
		if (colourTheme.equals(Themes.INVERSE.toString())) {
			colorDark = Color.DARK_GRAY;
			colorLight = hex2Rgb("#ECECEC");
			colorRect = Color.LIGHT_GRAY;
		}
		if (colourTheme.equals(Themes.BLUE.toString())) {
			colorDark = hex2Rgb("#19B5FE");
			colorLight = hex2Rgb("#336E7B");
			colorRect = hex2Rgb("#446CB3");
		}
		if (colourTheme.equals(Themes.GREEN.toString())) {
			colorDark = hex2Rgb("#36D7B7");
			colorLight = hex2Rgb("#1E824C");
			colorRect = hex2Rgb("#26A65B");
		}
		if (colourTheme.equals(Themes.RED.toString())) {
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
	 * @param colorStr e.g. "#FFFFFF"
	 * @return
	 */
	private static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(colorRect);
		g.fillRect(this.getX(), this.getY(), super.getWidth() - cellSize, super.getHeight() - cellSize);
		for (int i = 0; i < board.getBoardRows(); i++) {
			for (int j = 0; j < board.getBoardCols(); j++) {
				g.setColor(board.getCellAt(i, j).getCellState() ? colorDark : colorLight);
				g.fillRect(cellSize * i - 1, cellSize * j + 1, cellSize - 1, cellSize - 1);
			}
		}
		totalLiveCells = board.getCellAliveCount();
	}
}
