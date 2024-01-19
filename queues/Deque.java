/*****************************************************************************
 *  Name: Cynthia
 *  Date: Jan 15, 2024
 *  Description:
 *      Dequeue. A double-ended queue or deque (pronounced “deck”)
 *      is a generalization of a stack and a queue that supports adding
 *      and removing items from either the front or the back of the
 *      data structure.
 *      Create a generic data type Deque that implements the following API:
 *
 *
 *      Corner cases.  Throw the specified exception for the following corner cases:
 *
 *      Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
 *      Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast() when the deque is empty.
 *      Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 *      Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 *      Unit testing.  Your main() method must call directly every public constructor and method to help verify that they work as prescribed (e.g., by printing results to standard output).
 *****************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private Node first;
    private Node last;
    private int count;

    private class ListIterator implements Iterator<Item> {
        private Node curr;

        public ListIterator(Node initNode) {
            curr = initNode;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = curr.item;
            curr = curr.next;
            return item;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // construct an empty deque
    public Deque() {
        count = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (count == 0) {
            first = new Node();
            first.item = item;
            last = first;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        // Node newLast = new Node();
        // newLast.item = item;
        // if (last != null) {
        //     newLast.prev = last;
        //     last.next = newLast;
        // }
        // last = newLast;
        // if (first == null) {
        //     first = last;
        // }

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;

        if (isEmpty()) first = last;
        else oldLast.next = last;
        
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            first.next.prev = null;
            first = first.next;
        }
        count--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        if (count == 1) {
            first = null;
            last = null;
        }
        else {
            last.prev.next = null;
            last = last.prev;
        }
        count--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<>();

        System.out.println("IS EMPTY: " + deck.isEmpty());

        for (int i = 0; i < 10; i++) {
            deck.addFirst(i);
            System.out.println("SIZE: " + deck.size());
            System.out.println("IS EMPTY: " + deck.isEmpty());
        }

        System.out.println("Elements 0-9 added. We should have seen 1 to 10 printed");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println(
                "Finished iterating over the iterator. Elements should appear from 9 to 0.");

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.removeLast());
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 removed. They should appear from 0 to 9.");

        for (int i = 0; i < 10; i++) {
            deck.addLast(i);
            System.out.println("SIZE: " + deck.size());
            System.out.println("IS EMPTY: " + deck.isEmpty());
        }

        System.out.println("Elements 0-9 added.");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println(
                "Finished iterating over the iterator. Elements should appear from 0 to 9.");
    }

}
