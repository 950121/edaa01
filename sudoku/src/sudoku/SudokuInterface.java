package sudoku;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class SudokuInterface{

	private Solver sudoku;
	private BorderPane root;
	private TilePane tilepane;
	OneLetterTextField[][] fields = new OneLetterTextField[9][9];

	/**
	 * Constructor of the interface
	 */
	public SudokuInterface(Solver sudoku){
		this.sudoku = sudoku;
		
		tilepane = new TilePane();
		tilepane.setPrefColumns(9);
		tilepane.setPrefRows(9);
		tilepane.setHgap(3);
		tilepane.setVgap(3);
		tilepane.setAlignment(Pos.CENTER);
		tilepane.setMaxWidth(300);
		tilepane.setMinWidth(300);
		tilepane.setMaxHeight(300);
		tilepane.setMinHeight(300);
		
		for (int row = 0; row < 9; row++){
			
			for (int col = 0; col < 9; col++){
				OneLetterTextField field = new OneLetterTextField();
				int segment = row/3 + col/3;
				boolean coloured = (segment % 2) == 0;
				if (coloured){
					field.setStyle("-fx-background-color: orange;");
				}
				if (!coloured){
					field.setStyle("-fx-background-color: lightgrey;");
				}
				fields[row][col] = field;
				field.setPrefColumnCount(1);
				field.setPrefHeight(1);
				tilepane.getChildren().add(field);
			}
		}
		
		root = new BorderPane();
		root.setCenter(tilepane);
		root.setBottom(menu());
		root.setPrefSize(300,300);
		
	}

	/**
	 * Returns the root
	 * @return root
	 */
	public Parent getRoot(){
		return root;
	}
	
	/**
	 * Creates the menu for the window
	 * @return Clear and Solve output
	 */
	private HBox menu() {
		//Creates the buttons "Clear" and "Solve" 
		Button clear = new Button("Clear");
		clear.setOnAction(e -> clear());
		
		Button solve = new Button("Solve");
		solve.setOnAction(e -> solve());
		
		//Creates the HBox and adds the buttons to the HBox
		HBox hbButtons = new HBox();
		hbButtons.getChildren().addAll(clear, solve);
		hbButtons.setSpacing(10);
		hbButtons.setPadding(new Insets(10,10,10,10));
		return hbButtons;
	}

	/**
	 * Fills the boxes of the Sudoku , if a solution is possible.
	 */
	private void solve() {
		//hämtar ifyllda värden
		for (int row = 0; row < 9; row++){
			for (int col = 0; col < 9; col++){
				OneLetterTextField tf = fields[row][col];
				if (!tf.getText().isEmpty()){
					sudoku.set(Integer.parseInt(tf.getText()), row, col);
				} else{
					sudoku.set(0, row, col);
				}
			}
		}
		//kollar så att de hämtade värdena funkar
		if (sudoku.preCheck()){
			if (sudoku.solve()){
				for (int row = 0; row < 9; row++){
					for (int col = 0; col < 9; col++){
						OneLetterTextField tf = fields[row][col];
						tf.replaceText(0,0,""+ sudoku.get(row,col));
					}
				}
		} else{
			DialogWindow();
			}
		}
		else{
			DialogWindow();
		}
	}

	/**
	 * Clears the Sudoku board
	 */
	private void clear() {
		for (int row = 0; row < 9; row++){
			for (int col = 0; col < 9; col++){
				OneLetterTextField field = fields[row][col];
				//Kollar om ett fält har innehåll, ändrar sedan TextField samt modellen
				if (!field.getText().isEmpty()){
					field.replaceText(0,1,"");
					sudoku.set(0, row, col);
				}
			}
		}
	}

	/**
	 * Creates a dialog window
	 * @return alertWindow	An alert window is returned to show 
	 * 						the user that no solution exists
	 */
	private Alert DialogWindow(){
		Alert alertWindow = new Alert(AlertType.ERROR);
		alertWindow.setTitle("No solution");
		alertWindow.setHeaderText("No solutions found");
		alertWindow.setContentText("Close and start over");		
		alertWindow.showAndWait();
		
		return alertWindow;
	}
	
}
