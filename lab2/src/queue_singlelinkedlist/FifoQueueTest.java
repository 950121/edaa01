package queue_singlelinkedlist;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FifoQueueTest<E> {

	private FifoQueue<Integer> firstQueue, secondQueue;

	@Before
	public void setUp() throws Exception {
		firstQueue = new FifoQueue<Integer>();
		secondQueue = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		firstQueue = null;
		secondQueue = null;
	}

	
	@Test
	public void testTwoEmptyQueues() {	
		
		firstQueue.append(secondQueue);
		
		assertTrue("Queue not empty", firstQueue.isEmpty());
		assertTrue("Queue not empty", secondQueue.isEmpty());
	}
	
	@Test
	public void testEmptyToNonEmptyQueue() {
		Integer e1 = 1, e2 = 2;
		firstQueue.offer(e1);
		firstQueue.offer(e2);
		
		firstQueue.append(secondQueue);

		assertTrue("Empty queue not added correctly to empty queue", firstQueue.size()==2);
		assertTrue("Not correct order", firstQueue.poll()==e1);
		assertTrue("Not correct order", firstQueue.poll()==e2);
		
	}
	
	@Test
	public void testNonEmptyToEmptyQueue() {
		Integer e1 = 1, e2 = 2;
		secondQueue.offer(e1);
		secondQueue.offer(e2);
		
		firstQueue.append(secondQueue);

		assertTrue("Non empty queue not added correctly to empty queue", firstQueue.size()==2);
		assertTrue("Not correct order", firstQueue.poll()==e1);
		assertTrue("Not correct order", firstQueue.poll()==e2);
	}
	
	@Test
	public void testTwonNonEmptyQueues() {
		Integer e1 = 1, e2 = 2, e3 = 3;
		firstQueue.offer(e1);
		secondQueue.offer(e2);
		secondQueue.offer(e3);
		
		firstQueue.append(secondQueue);

		assertTrue("Non empty queue not added correctly to non empty queue", firstQueue.size()==3);
		assertTrue("secondQueue not empty", secondQueue.isEmpty());
		assertTrue("Not correct order", firstQueue.poll()==e1);
		assertTrue("Not correct order", firstQueue.poll()==e2);
		assertTrue("Not correct order", firstQueue.poll()==e3);
	}
	
	@Test
	public void testQueueIntoItself() {
		Integer e1 = 1;
		firstQueue.offer(e1);
		
		try {
			firstQueue.append(firstQueue);
			fail("Should raise IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			// successful test
		}
		
	}

}
