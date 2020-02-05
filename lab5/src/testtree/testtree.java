package testtree;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bst.BinarySearchTree;

public class testtree {
	private BinarySearchTree<Integer> myIntTree;
	private BinarySearchTree<String> myStringTree;

	@Before
	public void setUp() throws Exception {
		myIntTree = new BinarySearchTree<Integer>();
		myStringTree = new BinarySearchTree<String>();
	}

	@After
	public void tearDown() throws Exception {
		myIntTree = null;
		myStringTree = null;
	}
	
	@Test
	public void addTest() {
	
		assertTrue("Could not add element 1", myIntTree.add(1));
		assertTrue("Could not add element 2", myIntTree.add(2));
		assertTrue("Could not add element 3", myIntTree.add(3));


	}
	
	@Test
	public void heightTest() {
		
		
		for(int i = 0; i < 10; i++) {
			myIntTree.add(i);
		}
		
		System.out.println(myIntTree.height());
		assertEquals("Wrong height of Tree", 10, myIntTree.height());

	}
	
	@Test
	public void emptyHeightTest() {
		
		assertEquals("Wrong height of Tree", 0, myIntTree.height());

	}
	
	@Test
	public void emptySizeTest() {
		
		assertEquals("Wrong height of Tree", 0, myIntTree.size());

	}
	
	@Test
	public void sizeTest() {
	
			myIntTree.add(4);
			myIntTree.add(2);
			myIntTree.add(3);
			myIntTree.add(1);
			myIntTree.add(5);
		
		assertEquals("Wrong size of Tree", 5, myIntTree.size());

	}

	@Test
	public void addDoubleTest() {
	
		assertTrue("Could not add element 1", myIntTree.add(1));
		assertFalse("Could not re-add element 1", myIntTree.add(1));

	}
	
}
