package Functions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AlgorithmA {
	private int rows; 
	private int columns;  
	private Box[][] map; 
	private int startRow;
	private int startColumn;
	private int endRow;
	private int endColumn;
	private PriorityQueue<Box> open;
	private ArrayList<Box> close;
	private boolean solution = true;

	public AlgorithmA(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.startRow = -1;
		this.startColumn = -1;
		this.endRow = -1;
		this.endColumn = -1;
		this.map = new Box[rows][columns];
		Comparator<Box> comparator = new BoxComparator();
		this.open = new PriorityQueue<Box>(rows * columns, comparator);
		this.close = new ArrayList<Box>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Box box = new Box();
				box.setBoxRow(i);
				box.setBoxColumn(j);
				box.setType(4);
				this.map[i][j] = box;
			}
		}
	}

	/*Functions for Algorithm A**/
	public boolean outOfRange(int row, int column) {
		return (row < 0 || row >= this.rows || column < 0 || column >= this.columns);
	}	
	public void expandBox(Box box) {
		int row = box.getBoxRow();
		int column = box.getBoxColumn();

		/*Box de arriba*/
		if (!outOfRange(row - 1, column) && 
				!this.close.contains(this.map[row - 1][column]) && 
				this.map[row - 1][column].getType() != 2) {

			this.map[row - 1][column].setFatherRow(row);
			this.map[row - 1][column].setFatherColumn(column);
			this.map[row - 1][column].setValue(1 + distanceToEnd(row - 1, column));
			this.open.add(this.map[row - 1][column]);
		}

		/*Box de derecha/arriba*/
		if (!outOfRange(row - 1, column + 1) && 
				!this.close.contains(this.map[row - 1][column + 1]) && 
				this.map[row - 1][column + 1].getType() != 2) {

			this.map[row - 1][column + 1].setFatherRow(row);
			this.map[row - 1][column + 1].setFatherColumn(column);
			this.map[row - 1][column + 1].setValue(Math.sqrt(2) + distanceToEnd(row - 1, column + 1));
			this.open.add(this.map[row - 1][column + 1]);
		}

		/*Box de derecha*/
		if (!outOfRange(row, column + 1) && 
				!this.close.contains(this.map[row][column + 1]) && 
				this.map[row][column + 1].getType() != 2) {

			this.map[row][column + 1].setFatherRow(row);
			this.map[row][column + 1].setFatherColumn(column);
			this.map[row][column + 1].setValue(1 + distanceToEnd(row, column + 1));
			this.open.add(this.map[row][column + 1]);
		}

		/*Box de derecha/abajo*/
		if (!outOfRange(row + 1, column + 1) && 
				!this.close.contains(this.map[row + 1][column + 1]) && 
				this.map[row + 1][column + 1].getType() != 2) {

			this.map[row + 1][column + 1].setFatherRow(row);
			this.map[row + 1][column + 1].setFatherColumn(column);
			this.map[row + 1][column + 1].setValue(Math.sqrt(2) + distanceToEnd(row + 1, column + 1));
			this.open.add(this.map[row + 1][column + 1]);
		}

		/*Box de abajo*/
		if (!outOfRange(row + 1, column) && 
				!this.close.contains(this.map[row + 1][column]) && 
				this.map[row + 1][column].getType() != 2) {

			this.map[row + 1][column].setFatherRow(row);
			this.map[row + 1][column].setFatherColumn(column);
			this.map[row + 1][column].setValue(1 + distanceToEnd(row + 1, column));
			this.open.add(this.map[row + 1][column]);
		}

		/*Box de izquierda/abajo*/
		if (!outOfRange(row + 1, column - 1) && 
				!this.close.contains(this.map[row + 1][column - 1]) && 
				this.map[row + 1][column - 1].getType() != 2) {

			this.map[row + 1][column - 1].setFatherRow(row);
			this.map[row + 1][column - 1].setFatherColumn(column);
			this.map[row + 1][column - 1].setValue(Math.sqrt(2) + distanceToEnd(row + 1, column - 1));
			this.open.add(this.map[row + 1][column - 1]);
		}

		/*Box de izquerda*/
		if (!outOfRange(row, column - 1) && 
				!this.close.contains(this.map[row][column - 1]) && 
				this.map[row ][column - 1].getType() != 2) {

			this.map[row][column - 1].setFatherRow(row);
			this.map[row][column - 1].setFatherColumn(column);
			this.map[row][column - 1].setValue(1 + distanceToEnd(row, column - 1));
			this.open.add(this.map[row][column - 1]);
		}

		/*Box de izquerda/arriba*/
		if (!outOfRange(row - 1, column - 1) && 
				!this.close.contains(this.map[row - 1][column - 1]) && 
				this.map[row - 1][column - 1].getType() != 2) {

			this.map[row - 1][column - 1].setFatherRow(row);
			this.map[row - 1][column - 1].setFatherColumn(column);
			this.map[row - 1][column - 1].setValue(Math.sqrt(2) + distanceToEnd(row - 1, column - 1));
			this.open.add(this.map[row - 1][column - 1]);
		}
	}
	public double distanceToEnd(int row, int column) {
		double x = this.endColumn - column;
		double y = this.endRow - row;
		return Math.sqrt((x * x) + (y * y));
	}
	public void algorithmA() {
		Box box = this.open.peek();
		this.open.poll();
		this.close.add(box);
		expandBox(box);
		boolean end = false;
		while (!this.open.isEmpty() && !end) {
			box = this.open.peek();
			this.open.poll();
			this.close.add(box);
			if (!itsEnd(box)) expandBox(box);
			else end = true;
		}

		if (this.open.isEmpty() && !itsEnd(box)) this.solution = false;
		else {
			findCorrectWay();
		}
	}
	public void findCorrectWay() {
		int i = this.close.size() - 2;
		Box box = this.close.get(i);
		while (!itsStart(box)) {
			this.map[box.getBoxRow()][box.getBoxColumn()].setType(3);
			box = this.map[box.getFatherRow()][box.getFatherColumn()];
		}
	}

	/*Additional functions*/
	public boolean itsEnd(Box box) {
		return (box.getBoxRow() == this.endRow && box.getBoxColumn() == this.endColumn);
	}
	public boolean itsStart(Box box) {
		return (box.getBoxRow() == this.startRow && box.getBoxColumn() == this.startColumn);
	}
	public void showMap() {
		int type;
		System.out.print("  ");
		for (int j = 0; j < columns; j++) {
			System.out.print(j + 1 + " ");
		}
		System.out.println();

		for (int i = 0; i < rows; i++) {
			System.out.print(i + 1 + " ");
			for (int j = 0; j < columns; j++) {
				type = this.map[i][j].getType();
				if (type == 0) System.out.print("S");
				else if (type == 1) System.out.print("E");
				else if (type == 2) System.out.print("X");
				else if (type == 3) System.out.print(".");
				else System.out.print(" ");
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public boolean startExists() {
		return (this.startRow != -1 && this.startColumn != -1);
	}
	public boolean endExists() {
		return (this.endRow != -1 && this.endColumn != -1);
	}
	
	/*Setters and Getters*/
	public void setStart(int row, int column) {
		this.startRow = row;
		this.startColumn = column;
		this.map[startRow][startColumn].setType(0);
		this.open.add(map[startRow][startColumn]);
	}
	public void setEnd(int row, int column) {
		this.endRow = row;
		this.endColumn = column;
		this.map[endRow][endColumn].setType(1);
	}
	public void setWall(int row, int column) {
		map[row][column].setType(2);
	}
	public Box[][] getMap() {
		return this.map;
	}
	public boolean getSolution() {
		return this.solution;
	}
}