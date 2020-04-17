package application.Grid;

import application.MouseEvents.MouseEvents;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import application.Grid.Node;

public class Grid {
	
	public static Node start = new Node();
	public static Node end = new Node();
	int cellNumber;
	double width,height,offset;
	Pane m = new Pane();
	MouseEvents me = new MouseEvents();
	Node[][] nodes;
	boolean diag = false;
	
	public Grid(double width, double height, double offset, int cellNumber, Pane p) {
		m = p;
		this.width = width;
		this.height = height;
		this.cellNumber = cellNumber;
		this.offset = offset;
		nodes = new Node[cellNumber][cellNumber];
		for(int i = 0; i < cellNumber; i++) {
			for(int j = 0; j< cellNumber; j++) {
				Node node = new Node(j,i,(width - 2*offset) / cellNumber, (height-2*offset) / cellNumber);	
				node.setTranslateX(offset + i *node.getCellWidth());
				node.setTranslateY(offset + 20 + j*node.getCellHeight());
				node.setStroke(Color.DARKGRAY);
				nodes[i][j] = node;
				m.getChildren().add(node);
				me.attachEvents(node);
				
			}
		}	
	}
	
	public void reset(boolean full) {
		if(!full) {
			for(Node[] n : nodes) {
				for(Node node : n) {
					if(!(node.getColor().equals(Color.BLACK))) {
						continue;
					}
					node.paint(Color.TRANSPARENT);
				}
			}
		} else {
			m.getChildren().clear();
			for(int i = 0; i < cellNumber; i++) {
				for(int j = 0; j< cellNumber; j++) {
					Node node = new Node(j,i,(width - 2*offset) / cellNumber, (height-2*offset) / cellNumber);	
					node.setTranslateX(offset + i *node.getCellWidth());
					node.setTranslateY(offset + 20 + j*node.getCellHeight());
					node.setStroke(Color.DARKGRAY);
					nodes[i][j] = node;
					m.getChildren().add(node);
					me.attachEvents(node);
					
				}
			}	
		}
		me.reset();
		
	}
	
	public Node getStart() {
		return start;
	}
	
	public Node getEnd() {
		return end;
	}
	
	public int getLength() {
		return cellNumber;
	}
	
	public List<Node> getNeighbors(Node c, boolean diag) {
		int y = c.getRow();
		int x = c.getCol();
		List<Node> neighbors = new ArrayList<>(8);
		for(int i = x - 1; i <= x +1; i++) {
			for(int j = y - 1; j <= y +1; j++) {
				if (i >= 0 && j >= 0 && i < cellNumber && j < cellNumber) {
					if(j != y || i!=x) {
						if(!diag) {
							if(!nodes[i][j].getColor().equals(Color.BLACK) && Math.abs(j-y) + Math.abs(i-x) <= 1) {
								neighbors.add(nodes[i][j]);
							}
						}
							else if(!nodes[i][j].getColor().equals(Color.BLACK)) {
								neighbors.add(nodes[i][j]);
						}
					
					}
				}
			}
		}
		return neighbors;
	}

	public void setDiag(boolean val) {
		diag = val;
	}
	
	public boolean getDiag() {
		return diag;
	}
}
