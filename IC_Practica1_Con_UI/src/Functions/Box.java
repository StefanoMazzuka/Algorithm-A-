package Functions;

import javax.swing.JButton;

public class Box extends JButton{
	
	private int type;
	private int row;
	private int column;
	private int fatherRow;
	private int fatherColumn;
	private double value;
	
	public Box() {}
	
	/*Setters and Getters*/
	public int getType() {
		return this.type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBoxRow() {
		return this.row;
	}
	public void setBoxRow(int row) {
		this.row = row;
	}
	public void setBoxColumn(int column) {
		this.column = column;
	}
	public int getBoxColumn() {
		return this.column;
	}
	public void setFatherRow(int row) {
		this.fatherRow = row;
	}
	public int getFatherRow() {
		return this.fatherRow;
	}
	public void setFatherColumn(int column) {
		this.fatherColumn = column;
	}
	public int getFatherColumn() {
		return this.fatherColumn;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getValue() {
		return this.value;
	}
}