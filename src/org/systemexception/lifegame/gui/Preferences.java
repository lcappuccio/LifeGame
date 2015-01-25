package org.systemexception.lifegame.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Preferences extends JFrame {

	private JPanel prefsWindow;
	private int cellSize = 5, minCellSize = 1, maxCellSize = 10;
	private static int windowWidth = 190, windowHeight = 110;
	private JSpinner prefsCellSpinner;
	
	public int getWindowWidth() {
		return windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	/**
	 * Create the frame.
	 */
	public Preferences() {
		setResizable(false);
//		setType(Type.POPUP);
		setTitle("Preferences");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, windowWidth, windowHeight);
		prefsWindow = new JPanel();
		prefsWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(prefsWindow);
		
		JLabel prefsCellSize = new JLabel("Cell Size");
		
		SpinnerNumberModel prefsCellSpinnerModel = new SpinnerNumberModel(cellSize,minCellSize,maxCellSize,1);
		prefsCellSpinner = new JSpinner(prefsCellSpinnerModel);		
		
		JButton prefsSave = new JButton("Save");
		prefsSave.addActionListener(new ActionListener() {
			// Save cell value
			public void actionPerformed(ActionEvent e) {
				saveCellSize();
			}
		});
		JButton prefsCancel = new JButton("Cancel");
		prefsCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		
		GroupLayout gl_prefsWindow = new GroupLayout(prefsWindow);
		gl_prefsWindow.setHorizontalGroup(
			gl_prefsWindow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_prefsWindow.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_prefsWindow.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_prefsWindow.createSequentialGroup()
							.addGap(8)
							.addComponent(prefsCellSize)
							.addGap(28)
							.addComponent(prefsCellSpinner, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
						.addGroup(gl_prefsWindow.createSequentialGroup()
							.addComponent(prefsSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(prefsCancel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_prefsWindow.setVerticalGroup(
			gl_prefsWindow.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_prefsWindow.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_prefsWindow.createParallelGroup(Alignment.BASELINE)
						.addComponent(prefsCellSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(prefsCellSize))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_prefsWindow.createParallelGroup(Alignment.BASELINE)
						.addComponent(prefsSave)
						.addComponent(prefsCancel))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		prefsWindow.setLayout(gl_prefsWindow);
	}
	
	private int saveCellSize() {
		getCellValue();
		super.setVisible(false);
		return cellSize;
	}
	
	private void closeWindow() {
		super.setVisible(false);
	}
	
	private void getCellValue() {
		setCellSize(Integer.parseInt(prefsCellSpinner.getValue().toString()));
	}
}
