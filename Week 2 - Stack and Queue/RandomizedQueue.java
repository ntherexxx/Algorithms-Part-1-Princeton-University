import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int capacity; // Array Capacity
	private int N; // Number of Element in Array
	private Item[] s;

	public RandomizedQueue() {
		N = 0;
		capacity = 1;
		s = (Item[]) new Object[capacity];
	}                 // construct an empty randomized queue
   	public boolean isEmpty() {
   		return N == 0;
   	}                // is the randomized queue empty?
   	public int size() {
   		return N;
   	}                       // return the number of items on the randomized queue
   	public void enqueue(Item item) {
   		if (item == null) {
   			throw new IllegalArgumentException("enqueue item is null")
   		}
   		if (N == capacity) {
   			capacity = capacity * 2;
   			Item[] tmp = (Item[]) new Object[capacity];
   			int index = 0;
   			for (Item i : s) {
   				tmp[index++] = i;
   			}
   			s = tmp;
   		}
   		s[N++] = item;
   		N++;
   	}          // add the item
   	public Item dequeue() {
   		if (isEmpty()) {
   			throw new NoSuchElementException("dequeue has empty array");
   		}
   		int index = StdRandom.uniform(N);
   		Item tmpItem = s[index];

   		s[index] = s[--N];
   		s[--N] = null;
   		N--;

   		if (N <= (capacity / 4)) {
   			capacity /= 2;
   			Item[] tmp = (Item[]) new Object[capacity];
   			for (int i = 0; i < capacity; i++) {
   				tmp[i] = s[i];
   			}
   			s = tmp;
   		}
   		return tmpItem;
   	}                   // remove and return a random item
   	public Item sample() {
   		int index = StdRandom.uniform(N);
   		Item tmpItem = s[index];
   		return tmpItem;
   	}                    // return a random item (but do not remove it)
   	public Iterator<Item> iterator() {
   		return new ListIterator();
   	}        // return an independent iterator over items in random order

   	private class ListIterator implements Iterator<Item> {
   		private int current = 0;
   		private int[] shuffle = new int[N];

   		public boolean hasNext() {
   			if(current == 0)
   		}

   	}
   	public static void main(String[] args)   // unit testing (optional)
   }
