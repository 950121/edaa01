package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;

	}

	public void append(FifoQueue<E> q) {
		QueueNode<E> node;
		if (this == q) {
			throw new IllegalArgumentException();
		}

		if (q.size != 0 && size != 0) {
			node = q.last.next;
			q.last.next = last.next;
			last.next = node;
			last = q.last;

			size = size + q.size;
			q.last = null;
			q.size = 0;
		} else if (size == 0) {
			last = q.last;
			size = q.size;
			q.last = null;
		}

	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> node = new QueueNode<E>(e);

		if (size != 0) {
			node.next = last.next;
			last.next = node;
		} else {
			node.next = node;
		}

		last = node;
		size++;

		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) {
			return null;
		}
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}

		QueueNode<E> node = last.next;
		last.next = node.next;
		size--;
		return node.element;

	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		int i = size;

		private QueueIterator() {
			pos = last;

		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i != 0;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			pos = pos.next;
			i--;

			return pos.element;

		}

	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}

	}

}
