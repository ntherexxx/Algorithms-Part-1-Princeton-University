import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n; // size of deque
    private Node first;
    private Node last;
    // @SuppressWarnings("unchecked")
    private class Node
    {
        private Item item;
        private Node next;
        private Node prev;
    }
    public Deque()
    {
        n = 0;
        first = null;
        last = null;
    }                          // construct an empty deque
    public boolean isEmpty()
    {
        return n == 0;
    }                 // is the deque empty?
    public int size()
    {
        return n;
    }                        // return the number of items on the deque
    public void addFirst(Item item)
    {
        // Check if item is null
        if (item == null)
            throw new java.lang.IllegalArgumentException("add first have null item");
        
        // Case 1: many elements
        Node tmp = first;
        first = new Node();
        first.item = item;
        first.next = tmp;
        first.prev = null;
        n++;
        
        if (n == 1)
            last = first;
        else
            tmp.prev = first;
    }          
    // add the item to the front
    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.IllegalArgumentException("add last have null item");
        
        Node tmp = last;
        last = new Node();
        last.item = item;
        last.prev = tmp;
        last.next = null;
        n++;
        
        if (n == 1)
            first = last;
        else
            tmp.next = last;
    }           
    // add the item to the end
    public Item removeFirst()
    {
        // Case 1: No element
        if (isEmpty())
            throw new NoSuchElementException("removeFirst has no element");
        
        
        Item tmpItem = first.item;
        first = first.next;
        // Case 2: One Element
        // Set all to null and 0
        if (first != null) {
            first.prev = null;
        }
        n--;
        if (isEmpty()) {
            last = null;
        }
        return tmpItem;
    }                // remove and return the item from the front
    public Item removeLast()
    {
        // Case 1: No element
        if (isEmpty())
            throw new NoSuchElementException("removeLast has no element");
        
        Item tmpItem = last.item;
        last = last.prev;
        
        // Case 2: One Element
        // Set all to null and 0
        if (last != null) {
            last.next = null;
        }
        n--;
        if (isEmpty()) {
            first = null;
        }
        return tmpItem;
    }                 // remove and return the item from the end
    
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext() {
            return current != null;
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
        // nothing
    }   // unit testing (optional)
}
