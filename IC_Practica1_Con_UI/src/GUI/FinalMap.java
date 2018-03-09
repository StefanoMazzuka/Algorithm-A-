package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Functions.AlgorithmA;
import Functions.Box;

public class FinalMap extends JFrame{
	private int rows;
	private int columns;
	private Box[][] map;
	private AlgorithmA algorithm;
	JLabel messageTitle = new JLabel();
	JLabel message = new JLabel();

	public FinalMap(AlgorithmA algorithm, int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.map = algorithm.getMap();
		this.algorithm = algorithm;
			
		setSize(new Dimension(400, 300));
		setLocationRelativeTo(null); 
		setTitle("Algorithm A*"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Box box;
		JPanel finalMapPanel = new JPanel();
		finalMapPanel.setLayout(new GridLayout(this.rows, this.columns));
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				box = this.map[i][j];
				finalMapPanel.add(box);
				if (box.getType() == 0) box.setBackground(Color.GREEN);
				else if (box.getType() == 1) box.setBackground(Color.RED);
				else if (box.getType() == 2) box.setBackground(Color.BLACK);
				else if (box.getType() == 3) box.setBackground(Color.PINK);
				else box.setBackground(Color.WHITE);
				box.setOpaque(true);
			}
		}
		
		JPanel messages = new JPanel();
		messages.setLayout(new GridLayout(2, 1));
		if (this.algorithm.getSolution() == true) {
			this.messageTitle.setText("SUCCESS!");
			this.messageTitle.setHorizontalAlignment(JLabel.CENTER);
			this.message.setText("I find the way.");
			this.message.setHorizontalAlignment(JLabel.CENTER);
		}
		else {
			this.messageTitle.setText("FAIL!");
			this.messageTitle.setHorizontalAlignment(JLabel.CENTER);
			this.message.setText("Sorry, there is no possible way.");
			this.message.setHorizontalAlignment(JLabel.CENTER);
		}
		messages.add(messageTitle);
		messages.add(message);
		
		setLayout(new BorderLayout());
		add(finalMapPanel, BorderLayout.CENTER);
		add(messages, BorderLayout.PAGE_END);
		add(finalMapPanel);
	}
}