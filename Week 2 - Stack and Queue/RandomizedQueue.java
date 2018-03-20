import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
private int capacity; // Array Capacity
private int N; // Number of Element in Array
private Item[] s;

public RandomizedQueue() {
	N = 0;
	capacity = 1;
	s = (Item[]) new Object[capacity];
}                 
// construct an empty randomized queue
	public boolean isEmpty() {
		return N == 0;
	}                
	// is the randomized queue empty?
	public int size() {
		return N;
	}                       
	// return the number of items on the randomized queue
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("enqueue item is null");
		}

		s[N++] = item;
		
		if (N == capacity) {
			capacity = capacity * 2;
			Item[] tmp = (Item[]) new Object[capacity];
			int index = 0;
			for (Item i : s) {
				if (i != null) {
					tmp[index++] = i;
				}
			}
			s = tmp;
		}

	}          
	// add the item
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("dequeue has empty array");
		}

		int index = StdRandom.uniform(N);
		Item tmpItem = s[index];
		while (tmpItem == null) {
			index = StdRandom.uniform(N);
			tmpItem = s[index];
		}

		N--;

		// re-organize the random queue
		for (; index < (capacity - 1); index++) {
			s[index] = s[index + 1];
		}

		if (N <= (capacity / 4)) {
			capacity /= 2;
			Item[] tmp = (Item[]) new Object[capacity];
			index = 0;
			for (Item i : s) {
				if (i != null) {
					tmp[index++] = i;
				}
			}
			s = tmp;
		}

		return tmpItem;
	}                   
	// remove and return a random item
	public Item sample() {
		int index = StdRandom.uniform(N);
		Item tmpItem = s[index];
		while (tmpItem == null) {
			index = StdRandom.uniform(N);
			tmpItem = s[index];
		}
		return tmpItem;
	}                    
	// return a random item (but do not remove it)
	public Iterator<Item> iterator() {
		return new ListIterator();
	}        
	// return an independent iterator over items in random order
	private class ListIterator implements Iterator<Item> {
		public boolean hasNext() {
			return N > 0;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove is not supported");
		}

		public Item next() {
			int index = StdRandom.uniform(N);
			Item tmpItem = s[index];
			while (tmpItem == null) {
				index = StdRandom.uniform(N);
				tmpItem = s[index];
			}
			return tmpItem;
		}
	}
	public static void main(String[] args) {
		// Tests
	}  // unit testing (optional)
}
