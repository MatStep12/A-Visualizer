package application.MouseEvents;

import application.Grid.Grid;
import application.Grid.Node;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MouseEvents {

	boolean hasStart = false;
	boolean hasEnd = false;
	
	public void attachEvents(Node node) {
		node.setOnMousePressed(mousePressed);
		node.setOnDragDetected(mouseDetected);
		node.setOnMouseDragEntered(mouseDraggedEnter);
	}
	
	EventHandler<MouseEvent> mousePressed = event -> {
		Node cell = (Node) event.getSource();
		if(event.isShiftDown()) {
			if(hasStart) {
				Grid.start.paint(Color.TRANSPARENT);
			}
			hasStart = true;
			Grid.start = cell;
			cell.paint(Color.RED);
		} else if(event.isControlDown()) {
			if(hasEnd) {
				Grid.end.paint(Color.TRANSPARENT);
			}
			hasEnd = true;
			Grid.end = cell;	
			cell.paint(Color.LIMEGREEN);
		} else if(event.isPrimaryButtonDown()) {
			cell.paint(Color.BLACK);
		}	
	};
	
	EventHandler<MouseEvent> mouseDetected = event -> {
		Node cell = (Node) event.getSource();
		cell.startFullDrag();
	};
	
	EventHandler<MouseEvent> mouseDraggedEnter = event -> {
		Node cell = (Node) event.getSource();
		if(event.isPrimaryButtonDown() && !event.isShiftDown() && !event.isControlDown()) {
			cell.paint(Color.BLACK);
		}
	};
	
	EventHandler<MouseEvent> mouseDragRelease = event -> {
    };
    
    EventHandler<MouseEvent> mouseDragged = event -> {
    	Node cell = (Node) event.getSource();
    	if(event.isPrimaryButtonDown() && !event.isShiftDown() && !event.isControlDown()) {
    		cell.paint(Color.BLACK);
    	}
    };
    
    public void reset() {
    	hasStart = false;
    	hasEnd = false;
    }
	
}
