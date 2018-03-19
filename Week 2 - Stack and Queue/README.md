# Stacks and Queues
We consider two fundamental data types for storing collections of objects: the stack and the queue. We implement each using either a singly-linked list or a resizing array. We introduce two advanced Java features—generics and iterators—that simplify client code. Finally, we consider various applications of stacks and queues ranging from parsing arithmetic expressions to simulating queueing systems.

# Assignment 2
## Programming Assignment 2: Deques and Randomized Queues

Write a generic data type for a deque and a randomized queue. The goal of this assignment is to implement elementary data structures using arrays and linked lists, and to introduce you to generics and iterators.

**Dequeue.** A _double-ended queue_ or _deque_ (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure. Create a generic data type `Deque` that implements the following API:

> <pre>**public class Deque<Item> implements Iterable<Item> {**
>  **public Deque()** <font color="gray">// construct an empty deque</font>
>  **public boolean isEmpty()** <font color="gray">// is the deque empty?</font>
>  **public int size()** <font color="gray">// return the number of items on the deque</font>
>  **public void addFirst(Item item)** <font color="gray">// add the item to the front</font>
>  **public void addLast(Item item)** <font color="gray">// add the item to the end</font>
>  **public Item removeFirst()** <font color="gray">// remove and return the item from the front</font>
>  **public Item removeLast()** <font color="gray">// remove and return the item from the end</font>
>  **public Iterator<Item> iterator()** <font color="gray">// return an iterator over items in order from front to end</font>
>  **public static void main(String[] args)** <font color="gray">// unit testing (optional)</font>
> **}**
> </pre>

_Corner cases. _ Throw the specified exception for the following corner cases:

*   Throw a `java.lang.IllegalArgumentException` if the client calls either `addFirst()` or `addLast()` with a `null` argument.
*   Throw a `java.util.NoSuchElementException` if the client calls either `removeFirst()` or `removeLast` when the deque is empty.
*   Throw a `java.util.NoSuchElementException` if the client calls the `next()` method in the iterator when there are no more items to return.
*   Throw a `java.lang.UnsupportedOperationException` if the client calls the `remove()` method in the iterator.

_Performance requirements. _ Your deque implementation must support each deque operation (including construction) in _constant worst-case time_. A deque containing _n_ items must use at most 48_n_ + 192 bytes of memory and use space proportional to the number of items _currently_ in the deque. Additionally, your iterator implementation must support each operation (including construction) in _constant worst-case time_.

**Randomized queue.** A _randomized queue_ is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure. Create a generic data type `RandomizedQueue` that implements the following API:

> <pre>**public class RandomizedQueue<Item> implements Iterable<Item> {**
>  **public RandomizedQueue()** <font color="gray">// construct an empty randomized queue</font>
>  **public boolean isEmpty()** <font color="gray">// is the randomized queue empty?</font>
>  **public int size()** <font color="gray">// return the number of items on the randomized queue</font>
>  **public void enqueue(Item item)** <font color="gray">// add the item</font>
>  **public Item dequeue()** <font color="gray">// remove and return a random item</font>
>  **public Item sample()** <font color="gray">// return a random item (but do not remove it)</font>
>  **public Iterator<Item> iterator()** <font color="gray">// return an independent iterator over items in random order</font>
>  **public static void main(String[] args)** <font color="gray">// unit testing (optional)</font>
> **}**
> </pre>

_Iterator_.  Each iterator must return the items in uniformly random order. The order of two or more iterators to the same randomized queue must be _mutually independent_; each iterator must maintain its own random order.

_Corner cases. _ Throw the specified exception for the following corner cases:

*   Throw a `java.lang.IllegalArgumentException` if the client calls `enqueue()` with a `null` argument.
*   Throw a `java.util.NoSuchElementException` if the client calls either `sample()` or `dequeue()` when the randomized queue is empty.
*   Throw a `java.util.NoSuchElementException` if the client calls the `next()` method in the iterator when there are no more items to return.
*   Throw a `java.lang.UnsupportedOperationException` if the client calls the `remove()` method in the iterator.

_Performance requirements. _ Your randomized queue implementation must support each randomized queue operation (besides creating an iterator) in _constant amortized time_. That is, any sequence of _m_ randomized queue operations (starting from an empty queue) must take at most _cm_ steps in the worst case, for some constant _c_. A randomized queue containing _n_ items must use at most 48_n_ + 192 bytes of memory. Additionally, your iterator implementation must support operations `next()` and `hasNext()` in _constant worst-case time_; and construction in _linear time_; you may (and will need to) use a linear amount of extra memory per iterator.

**Client.** Write a client program `Permutation.java` that takes an integer _k_ as a command-line argument; reads in a sequence of strings from standard input using `StdIn.readString()`; and prints exactly _k_ of them, uniformly at random. Print each item from the sequence at most once.

> <pre>% **more [distinct.txt](../testing/queues/distinct.txt)**                        % **more [duplicates.txt](../testing/queues/duplicates.txt)**
> A B C D E F G H I                          AA BB BB BB BB BB CC CC
> 
> % **java-algs4 Permutation 3 < distinct.txt**   % **java-algs4 Permutation 8 < duplicates.txt**
> C                                               BB
> G                                               AA
> A                                               BB
>                                                 CC
> % **java-algs4 Permutation 3 < distinct.txt**       BB
> E                                               BB
> F                                               CC
> G                                               BB
> </pre>

Your program must implement the following API:

> <pre> **public class Permutation {
>    public static void main(String[] args)
> }** 
> </pre>

_Command-line input. _ You may assume that 0 ≤ _k_ ≤ _n_, where _n_ is the number of string on standard input.

_Performance requirements. _ The running time of `Permutation` must be linear in the size of the input. You may use only a constant amount of memory plus either one `Deque` or `RandomizedQueue` object of maximum size at most _n_. (For an extra challenge, use only one `Deque` or `RandomizedQueue` object of maximum size at most _k_.)

**Deliverables.** Submit the programs `RandomizedQueue.java`, `Deque.java`, and `Permutation.java`. Your submission may not call library functions except those in [`StdIn`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdIn.html), [`StdOut`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdOut.html), [`StdRandom`](https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdRandom.html), [`java.lang`](http://docs.oracle.com/javase/8/docs/api/java/lang/package-summary.html), [`java.util.Iterator`](http://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html), and [`java.util.NoSuchElementException`](http://docs.oracle.com/javase/8/docs/api/java/util/NoSuchElementException.html). In particular, do not use either [`java.util.LinkedList`](http://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html) or [`java.util.ArrayList`](http://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html).

<address><small>This assignment was developed by Kevin Wayne.  
Copyright © 2005.</small></address>