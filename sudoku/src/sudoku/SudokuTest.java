package sudoku;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SudokuTest {
	private Solver sudoku;

	@Before
	public void setUp() throws Exception {
		sudoku = new Solver();
	}

	@After
	public void tearDown() throws Exception {
		sudoku = null;
	}
	
	@Test
	public void emptySolve() {
		assertTrue("Could not solve empty Sudoku", sudoku.solve());
	}

	@Test
	public void sameRow() {
		sudoku.set(1, 0, 0);
		sudoku.set(1, 0, 3);
		assertFalse("Does not return false on unsolveable row", sudoku.solve());
	}
	
	@Test
	public void sameCol() {
		sudoku.set(1, 0, 0);
		sudoku.set(1, 3, 0);
		assertFalse("Does not return false on unsolveable column", sudoku.solve());
	}
	
	@Test
	public void sameBox() {
		sudoku.set(1, 0, 0);
		sudoku.set(1, 1, 1);
		assertFalse("Does not return false on unsolveable box", sudoku.solve());
	}
	
	@Test
	public void figure1Sudoku() {
		sudoku.set(8, 0, 2);
		sudoku.set(9, 0, 5);
		sudoku.set(6, 0, 7);
		sudoku.set(2, 0, 8);
		
		sudoku.set(5, 1, 8);
		
		sudoku.set(1, 2, 0);
		sudoku.set(2, 2, 2);
		sudoku.set(5, 2, 3);
		
		sudoku.set(2, 3, 3);
		sudoku.set(1, 3, 4);
		sudoku.set(9, 3, 7);
		
		sudoku.set(5, 4, 1);
		sudoku.set(6, 4, 6);
		
		sudoku.set(6, 5, 0);
		sudoku.set(2, 5, 7);
		sudoku.set(8, 5, 8);

		sudoku.set(4, 6, 0);
		sudoku.set(1, 6, 1);
		sudoku.set(6, 6, 3);
		sudoku.set(8, 6, 5);
		
		sudoku.set(8, 7, 0);
		sudoku.set(6, 7, 1);
		sudoku.set(3, 7, 4);
		sudoku.set(1, 7, 6);
		
		sudoku.set(7, 8, 6);
		
		assertTrue("Can't solve the figure one Sudoku", sudoku.solve());
	}
	
}
