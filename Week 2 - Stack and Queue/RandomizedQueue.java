import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int capacity; // Array Capacity
    private int n; // Number of Element in Array
    private Item[] s;
    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
      n = 0;
      capacity = 1;
      s = (Item[]) new Object[capacity];
    }
    // construct an empty randomized queue
    public boolean isEmpty() {
      return n == 0;
    }
    // is the randomized queue empty?
    public int size() {
      return n;
    }
    // return the number of items on the randomized queue
    public void enqueue(Item item) {
      if (item == null)
        throw new IllegalArgumentException("enqueue item is null");

      s[n] = item;
      n++;

      if (n == capacity) {
        capacity = capacity * 2;
        Item[] tmp = (Item[]) new Object[capacity];
        int index = 0;
        for (Item i : s) {
          if (i != null) {
            tmp[index] = i;
            index++;
          }
        }
        s = tmp;
      }
    }
    // add the item
    public Item dequeue() {
        // Check valid call
      if (isEmpty()) {
        throw new NoSuchElementException("dequeue has empty array");
      }
        // Pick a random item
      int index = StdRandom.uniform(n);
      Item tmpItem = s[index];
      while (tmpItem == null) {
        index = StdRandom.uniform(n);
        tmpItem = s[index];
      }

      s[index] = s[n - 1];
      s[n - 1] = null;
        // Remove the item
      n--;

      if (n <= (capacity / 4)) {
        capacity /= 2;
        Item[] tmp = (Item[]) new Object[capacity];
        index = 0;
        for (Item i : s) {
          if (i != null) {
            tmp[index] = i;
            index++;
          }
        }
        s = tmp;
      }

      return tmpItem;
    }
    // remove and return a random item
    public Item sample() {
      if (isEmpty()) {
        throw new NoSuchElementException("dequeue has empty array");
      }
      int index = StdRandom.uniform(n);
      Item tmpItem = s[index];
      while (tmpItem == null) {
        index = StdRandom.uniform(n);
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
      private int i = 0;
      private int[] randomItem = new int[n];

      private void fill() {
        for (int index = 0; index < n; index++) {
          randomItem[index] = index;
        }
      }

      private void shuffle() {
        StdRandom.shuffle(randomItem);
      }

      private void init() {
        if (i == 0) {
          fill();
          shuffle();
        }
      }

      public boolean hasNext() {
        init();
        return i < n;
      }

      public void remove() {
        throw new UnsupportedOperationException("remove is not supported");
      }

      public Item next() {
        init();
        if (!hasNext()) throw new NoSuchElementException("next has no element");
        return s[randomItem[i++]];
      }
    }
    public static void main(String[] args) {
        // Tests
    }  // unit testing (optional)
  }
