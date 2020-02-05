package sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private Solver solver; 

	public static void main(String[] args){
		Application.launch(args);
	}
	
	/**
	 * Starts the program , and the interface
	 */
	@Override
	public void start(Stage stage) {
		solver = new Solver();
		SudokuInterface gui = new SudokuInterface(solver);
		Scene scene = new Scene(gui.getRoot());
		
		stage.setTitle("Sudoku");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Exits the program
	 */
	@Override
	public void stop() {
		System.exit(0);
	}
}