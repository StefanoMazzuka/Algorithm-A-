package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.AlgorithmConstraints;
import java.security.AllPermission;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Functions.AlgorithmA;
import Functions.Box;

public class Map extends JFrame{
	private int boxValue;
	private int rows;
	private int columns;
	private AlgorithmA algorithm;

	public Map(int rows, int columns) {
		this.boxValue = -1;
		this.rows = rows;
		this.columns = columns;
		this.algorithm = new AlgorithmA(this.rows, this.columns);

		JButton start = new JButton("Start");
		JButton end = new JButton("End");
		JButton wall = new JButton("Wall");
		JButton run = new JButton("Run Algorithm A*");

		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Algorithm A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new GridLayout(this.rows, this.columns));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Box box = new Box();
				box.setBoxRow(i);
				box.setBoxColumn(j);
				box.setBackground(Color.WHITE);
				mapPanel.add(box);
				box.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (getBoxValue() == 0) {
							if (!startExists()) {
								setStart(box);
								box.setBackground(Color.GREEN);
								box.setOpaque(true);
							}			
						}
						else if (getBoxValue() == 1) {
							if (!endExists()) {
								setEnd(box);
								box.setBackground(Color.RED);
								box.setOpaque(true);
							}
						}
						else if (getBoxValue() == 2) {
							setWall(box);
							box.setBackground(Color.BLACK);
							box.setOpaque(true);
						} 
					}
				});
			}
		}

		JPanel controlButtons = new JPanel();
		controlButtons.setLayout(new GridLayout(1, 3));
		controlButtons.add(start);
		controlButtons.add(end);
		controlButtons.add(wall);

		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(2, 1));
		buttons.add(controlButtons);
		buttons.add(run);

		setLayout(new BorderLayout());
		add(mapPanel, BorderLayout.CENTER);
		add(buttons, BorderLayout.PAGE_END);

		/*Start button*/
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setBoxValue(0);
			}
		});
		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setBoxValue(1);
			}
		});
		wall.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setBoxValue(2);
			}
		});
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!startExists() || !endExists()) 
					JOptionPane.showMessageDialog(null, "ERROR: Please insert the start point" + "\nand the end point.");
				else {
					runAlgorithmA();
					updateMap();
				}
			}
		});
	}

	public void setBoxValue(int boxValue) {
		this.boxValue = boxValue;
	}
	public int getBoxValue() {
		return this.boxValue;
	}
	public boolean startExists() {
		return this.algorithm.startExists();
	}
	public void setStart(Box box) {
		this.algorithm.setStart(box.getBoxRow(), box.getBoxColumn());
	}
	public boolean endExists() {
		return this.algorithm.endExists();
	}
	public void setEnd(Box box) {
		this.algorithm.setEnd(box.getBoxRow(), box.getBoxColumn());
	}
	public void setWall(Box box) {
		this.algorithm.setWall(box.getBoxRow(), box.getBoxColumn());
	}
	public void runAlgorithmA() {
		this.algorithm.algorithmA();
	}
	public void updateMap() {
		FinalMap finalMap = new FinalMap(this.algorithm, this.rows, this.columns);
		setVisible(false);
		finalMap.setVisible(true);
	}
}
