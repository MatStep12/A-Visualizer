package application.Grid;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Node extends StackPane {
	double nodeWidth, nodeHeight;
	Rectangle Node;
	boolean isStart;
	boolean isEnd;
	Color c;
	double g,f,h;
	int col, row;
	Node cameFrom;
	
	public Node(int row, int col, double width, double height) {
		nodeWidth = width;
		nodeHeight = height;
		this.row = row;
		this.col = col;
		Node = new Rectangle(width, height);
		Node.setStroke(Color.BLACK);
		Node.setFill(null);
		getChildren().addAll(Node);
	}
	
	public Node() {
	}
	
	public double getCellHeight() {
		return nodeHeight;
	}
	
	public double getCellWidth() {
		return nodeWidth;
	}
	
	public boolean isStart() {
		return isStart;
	}
	
	public boolean isEnd() {
		return isEnd;
	}
	
	public void setStroke(Color c) {
		this.c = c;
		Node.setStroke(c);
	}
	
	public void paint(Color c) { 
		this.c = c;
		Node.setFill(c);
	}
	
	public Color getColor() {
		return c;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}
	
	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}
	
	public double getH() {
		return h;
	}

	public void setH(double h) {
		this.h = h;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public Node getCameFrom() {
		return cameFrom;
	}
	
	public void setCameFrom(Node c) {
		cameFrom = c;
	}
	
	
}
