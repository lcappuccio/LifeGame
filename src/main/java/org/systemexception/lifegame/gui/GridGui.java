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
import java.util.List;

public class GridGui extends JComponent {

	private final int gridRows;
    private final int gridCols;
	private final int cellSize;
    private int totalLiveCells;

	private Color colorDark = Color.DARK_GRAY;
    private Color colorLight = Color.WHITE;

    private transient Board board;

	public GridGui(final int cellSize, int gridRows, int gridCols, String colourTheme) {
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
                // not implemented
            }

			@Override
			public void mouseReleased(MouseEvent e) {
                // not implemented
            }

			@Override
			public void mouseEntered(MouseEvent e) {
                // not implemented
            }

			@Override
			public void mouseExited(MouseEvent e) {
                // not implemented
            }
		});
		totalLiveCells = board.getCellAliveCount();
		setColours(colourTheme);
	}

	public GridGui(int cellSize, int gridRows, int gridCols, List<List<String>> savedBoard, String
			colourTheme) {
		this.cellSize = cellSize;
		this.gridRows = gridRows;
		this.gridCols = gridCols;
		this.board = new Board(gridRows, gridCols, savedBoard);
		totalLiveCells = board.getCellAliveCount();
		setColours(colourTheme);
		this.paint(getGraphics());
	}

	/**
	 * Resets the board
	 */
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

	/**
	 * Iterates the board using the chosen automata
	 */
	public void iterateBoard() {
		board.iterateBoard();
		totalLiveCells = board.getCellAliveCount();
		this.repaint();
	}

	public int getTotalLiveCells() {
		return totalLiveCells;
	}

	/**
     * Colours from: <a href="http://www.flatuicolorpicker.com">...</a>
     *
     * @param colourTheme is the colour enum value
     */
	public void setColours(String colourTheme) {
		if (colourTheme.equals(Themes.BW.toString())) {
			colorDark = Color.DARK_GRAY;
			colorLight = Color.WHITE;
		}
		if (colourTheme.equals(Themes.INVERSE.toString())) {
			colorDark = Color.WHITE;
			colorLight = Color.DARK_GRAY;
		}
		if (colourTheme.equals(Themes.BLUE.toString())) {
			colorDark = hex2Rgb("#336E7B");
			colorLight = hex2Rgb("#19B5FE");
		}
		if (colourTheme.equals(Themes.GREEN.toString())) {
			colorDark = hex2Rgb("#1E824C");
			colorLight = hex2Rgb("#36D7B7");
		}
		if (colourTheme.equals(Themes.RED.toString())) {
			colorDark = hex2Rgb("#96281B");
			colorLight = hex2Rgb("#EF4836");
		}
	}

	/**
     * As seen on:
     * <a href="http://stackoverflow.com/questions/4129666/how-to-convert-hex-">...</a>
     * to-rgb-using-java
     *
     * @param colorStr e.g. "#FFFFFF"
     * @return the colour in RGB fashion
     */
	private static Color hex2Rgb(String colorStr) {
		return new Color(Integer.valueOf(colorStr.substring(1, 3), 16), Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	@Override
	public void paintComponent(Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D) graphics;
        for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j <gridCols; j++) {
                graphics2D.setColor(board.getCellAt(i, j).getCellState() ? colorLight : colorDark);
                graphics2D.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
            }
		}
		totalLiveCells = board.getCellAliveCount();
	}
}
