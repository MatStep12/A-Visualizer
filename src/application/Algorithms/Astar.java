package application.Algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import application.Grid.Grid;
import application.Grid.Node;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Astar {
	
	int gridLength;
	Node start, end;
	int delay = 100;
	boolean finished = false;
	public Astar() {
	}
	List<Node> solution = new ArrayList<>();
	boolean diag = false;
	
	public List<Node> solve(Grid g) {
		diag = g.getDiag();
		Timeline timeline = new Timeline();
		start = g.getStart();
		end = g.getEnd();
		Node current = null;
		gridLength = g.getLength() * g.getLength();
		Set<Node> closed = new HashSet<>(gridLength);
		PriorityQueue<Node> open = new PriorityQueue<Node>(gridLength, new NodeCompare());
		open.add(start);
		start.setG(0);
		start.setF(start.getG() + hcost(start, end));
		while(!open.isEmpty()) {
			current = open.poll();
			if(current.equals(end)) {
				solution = createPath(end);
				timeline.play();
				timeline.setOnFinished(e -> displayPath(solution));
				return solution;
			}
			
			closed.add(current);
			for(Node n : g.getNeighbors(current,diag)){
				if(closed.contains(n)) {
					continue;
				}
				double gscore = current.getG() + hcost(current, n);
				if(!open.contains(n) || gscore < n.getG()) {;
					timeline.getKeyFrames().add(new KeyFrame(Duration.millis(delay), e -> n.paint(Color.PINK)));
					delay+=10;
					n.setCameFrom(current);
					n.setG(gscore);
					n.setH(hcost(n,end));
					n.setF(n.getH() + n.getG());
				}
				if(!open.contains(n)) {
					open.add(n);
				}
			}
		}
		
		return new ArrayList<>();
	}
	
	public double hcost(Node start, Node end) {
		return Math.sqrt(Math.pow(end.getCol() - start.getCol(), 2) + Math.pow(end.getRow() - start.getRow(), 2));		
	}
	
	public List<Node> createPath(Node end){
		List<Node> path = new ArrayList<>(gridLength / 2);
		path.add(end);
		while( (end = end.getCameFrom()) != null) {
			path.add(end);
		       
		}
		return path;
	}

	public void displayPath(List<Node> solution ) {
		for(Node n  : solution) {
			if(!n.getColor().equals(Color.RED)) {
				if(!n.getColor().equals(Color.LIMEGREEN)) {
					n.paint(Color.YELLOW);
				}
			}
		}
	}
	public class NodeCompare implements Comparator<Node>
	{
	    @Override
	    public int compare(Node a, Node b)
	    {
	    	return Double.compare(a.getF(), b.getF());
	    }
	}
	
	
}
