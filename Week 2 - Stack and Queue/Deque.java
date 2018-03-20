import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private int n; // size of deque

	private class Node
	{
		private Item item;
		private Node next;
		private Node prev;
	}

	private Node first;
	private Node last;

	public Deque()
	{
		n = 0;
		first = null;
		last = null;
	}                          // construct an empty deque
	public boolean isEmpty()
	{
		return n > 0;
	}                 // is the deque empty?
	public int size()
	{
		return n;
	}                        // return the number of items on the deque
	public void addFirst(Item item)
	{
		// Check if item is null
		if (item == null) {
			throw new java.lang.NullPointerException("add first have null item");
		}

		// Case 1: many elements
		Node tmp = first;
		first = new Node();
		first.item = item;
		first.next = tmp;
		first.prev = null;
		n++;

		if (n == 1) {
			last = first;
		}
	}          // add the item to the front
	public void addLast(Item item)
	{
		if (item == null) {
			throw new java.lang.NullPointerException("add last have null item");
		}

		Node tmp = last;
		last = new Node();
		last.item = item;
		last.prev = tmp;
		last.next = null;
		n++;

		if (n == 1) {
			last = first;
		}
	}           // add the item to the end
	public Item removeFirst()
	{
		// Case 1: No element
		if (isEmpty())
		throw new java.util.NoSuchElementException("removeFirst has no element");


		Item tmpItem = first.item;
		Node firstNext = first.next;

		// Case 2: One Element
		// Set all to null and 0
		if (firstNext == null) {
			first = null;
			last = null;
			n = 0;
			return tmpItem;
		}

		// Case 3: More than one element
		// First point to its next and Last not move
		first = firstNext;
		first.prev = null;

		n--;
		return tmpItem;
	}                // remove and return the item from the front
	public Item removeLast()
	{
		// Case 1: No element
		if (isEmpty())
		throw new java.util.NoSuchElementException("removeLast has no element");

		Item tmpItem = last.item;
		Node lastPrev = last.prev;

		// Case 2: One Element
		// Set all to null and 0
		if (lastPrev == null) {
			first = null;
			last = null;
			n = 0;
			return tmpItem;
		}

		// Case 3: More than one element
		// First point to its next and Last not move
		last = lastPrev;
		last.next = null;

		n--;
		return tmpItem;
	}                 // remove and return the item from the end

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;

		public boolean hasNext() {
			return current.next != null;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove is not supported");
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("next cannot be performed.");
			}
			Item tmpItem = current.item;
			current = current.next;
			return tmpItem;
		}
	}

	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}         // return an iterator over items in order from front to end

	public static void main(String[] args)
	{
		//nothing
	}   // unit testing (optional)
}
