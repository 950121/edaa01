package sudoku;

import java.util.LinkedList;

public class Solver {

	private int[][] grid; //= new int[9][9];
	
	public Solver() {
		grid = new int[9][9];
	}

	public void set(int input, int row, int col){
		grid[row][col] = input;
	}
	
	public int get(int row, int col){
		return grid[row][col];
	}
	
	public boolean solve() {
		return solve(0,0);
	}
	
	/**
	 * Solves the sudoku by recursion
	 * @return boolean True when a solution has been found
	 */
	private boolean solve(int row, int col) {
		int nextCol = (col + 1) % 9;
		int nextRow = (nextCol == 0) ? row + 1 : row;
		if (row > 8){
			return true;
		}
		if (grid[row][col] == 0){
			for (int k = 1; k <= 9; k++){
				if (!gridCheck(k, row, col) && !lineCheck(k, row, col)){
					set(k, row, col);
					if (solve(nextRow, nextCol)){
						return true;
						}
					}
				}
				set(0, row, col);
			}else{
				if (!gridCheck(get(row,col),row,col) && 
						!lineCheck(get(row,col),row,col)){
					if (solve(nextRow, nextCol)){
						return true;
					}
				}
			}
		return false;
	}
	
	/**
	 * Checks matrix for faulty values before solve-recursion
	 * @return boolean True if no faulty values, else false
	 */
	public boolean preCheck(){
		for (int row = 0; row < 9; row++){
			for (int col = 0; col < 9; col++){
				if (get(row,col) != 0){
					if (lineCheck(get(row, col), row, col) 
							|| gridCheck(get(row,col), row, col)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks if the input can be placed at row,col
	 */
	private boolean lineCheck(int input, int row, int col) {
		
		boolean lineCheck = false;
		
		//Checks the row and column for double values
		
		for (int k = 0; k < 9; k++){
			if (k!=row && grid[k][col] == input ){
				return lineCheck = true;
			}
			if (k!=col && grid[row][k] == input){
				return lineCheck = true;
			} 
		}
		return lineCheck;
	}
	
	/**
	 * Checks if the pre filled boxes are correct
	 * @return true if the boxes are filled correctly
	 */
	private boolean gridCheck(int input, int row, int col) {
		
		LinkedList<Integer> box = new LinkedList<Integer>();
		
		for (int k = 0; k < 3; k++){
			for (int l = 0; l < 3; l++){
				if ((k + (row - row%3) != row) && (l + (col - col%3) != col)){
					box.add(grid[k + (row - row%3)][l + (col - col%3)]);
				}
			}
		}		

		return box.contains(input);
		
	}
	
}
