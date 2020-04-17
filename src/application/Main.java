package application;
	
import java.util.ArrayList;
import java.util.List;

import application.Algorithms.Astar;
import application.Grid.Grid;
import application.Grid.Node;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Main extends Application {

	Pane g = new Pane();
	Pane root = new Pane();
	double width = 1280.0;
	double height = 720.0;	
	double offset = 40.0;
	int nodeNumber = 40;
	List<Node> solution = new ArrayList<>();
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Grid grid = new Grid(width, height, offset, nodeNumber, g);
			HBox actions = new HBox();
			actions.setAlignment(Pos.TOP_CENTER);
			actions.setPadding(new Insets(10,10,10,10));
			Button reset = new Button("Reset Grid");
			reset.setOnAction(e -> {
				grid.setDiag(false);
				grid.reset(true);
			});
			actions.getChildren().add(reset);
			Button removeObstacles =new Button("Remove Obstacles");
			removeObstacles.setOnAction(e -> {
				grid.reset(false);
			});
			actions.getChildren().add(removeObstacles);
			Button AStar = new Button("AStar Algo");
			AStar.setOnAction(e -> {
			    Astar algo = new Astar();
				solution = algo.solve(grid);
				if(solution.size() == 0) {
					System.out.println("test");
				}
			});
			actions.getChildren().add(AStar);
			CheckBox diag = new CheckBox("Allow Diagonals?");
			diag.setOnAction(e ->{
				grid.setDiag(true);
			});
			actions.getChildren().add(diag);
			root.getChildren().add(actions);
			root.getChildren().add(g);
			actions.toFront();
			primaryStage.setScene(new Scene(root,width,height));
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
