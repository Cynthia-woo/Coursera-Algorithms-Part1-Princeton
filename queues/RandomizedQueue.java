/******************************************************************************
 *  Name: Cynthia
 *  Date: Jan 16, 2024
 *  Description:
 *      Randomized queue.
 *      A randomized queue is similar to a stack or queue,
 *      except that the item removed is chosen uniformly at random
 *      among items in the data structure.
 *      Create a generic data type RandomizedQueue that implements the following API:
 *
 *      Iterator.
 *      Each iterator must return the items in uniformly random order.
 *      The order of two or more iterators to the same randomized queue must be mutually independent;
 *      each iterator must maintain its own random order.
 *
 *      Corner cases.  Throw the specified exception for the following corner cases:
 *      Throw an IllegalArgumentException if the client calls enqueue() with a null argument.
 *      Throw a java.util.NoSuchElementException if the client calls either sample() or dequeue() when the randomized queue is empty.
 *      Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
 *      Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 *
 *      Unit testing.  Your main() method must call directly every public constructor
 *      and method to verify that they work as prescribed (e.g., by printing results to standard output).
 *****************************************************************************/

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] elements;
    private int size;
    // size of a full array
    private int numElements;
    // current element number in an array


    // construct an empty randomized queue
    public RandomizedQueue() {
        elements = (Item[]) new Object[1];
        size = 1;
        numElements = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return numElements == 0;
    }

    private boolean isFull() {
        return size == size();
    }

    private boolean isAlmostEmpty() {
        return size() <= size / 4;
    }

    // return the number of items on the randomized queue
    public int size() {
        return numElements;
    }

    private void doubleSize() {
        resize(2 * size);
    }

    private void halfSize() {
        resize(size / 2);
    }

    private void resize(int arraySize) {
        if (size() == 0) return;
        Item[] newArray = (Item[]) new Object[arraySize];
        for (int i = 0; i < numElements; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
        size = arraySize;
    }

    private void validateElement(Item item) {
        if (item == null) throw new IllegalArgumentException("The item cannot be null");
    }

    private void validateArrayEmpty() {
        if (isEmpty()) throw new NoSuchElementException("No such element");
    }


    // add the item
    public void enqueue(Item item) {
        validateElement(item);
        if (isFull()) doubleSize();
        elements[numElements] = item;
        numElements++;
    }

    private int randomIndex() {
        return StdRandom.uniformInt(size());
    }

    // remove and return a random item
    public Item dequeue() {
        validateArrayEmpty();

        int index = randomIndex();

        Item item = elements[index];
        elements[index] = elements[numElements - 1];
        elements[numElements - 1] = null;
        numElements--;

        if (isAlmostEmpty()) halfSize();
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        validateArrayEmpty();
        return elements[randomIndex()];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] copy;
        private int copySize;
        private int curr;

        public RandomizedQueueIterator() {
            copySize = size();
            copy = new int[size];
            curr = 0;
            for (int i = 0; i < copySize; i++) {
                copy[i] = i;
            }
            StdRandom.shuffle(copy);
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("There are no more elements");
            Item item = (Item) elements[copy[curr]];
            curr++;
            return item;
        }

        public boolean hasNext() {
            return copySize > curr;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "This operation is not supported by this class");
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        System.out.println("-isempty " + rq.isEmpty());

        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");

        System.out.println("-isempty " + rq.isEmpty());

        rq.enqueue("one");
        rq.enqueue("two");
        rq.enqueue("three");
        rq.enqueue("four");

        for (String s : rq) {
            System.out.println("  " + s);
        }

        System.out.println("-dequeued size " + rq.size());

        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());
        System.out.println("-sample " + rq.sample());

        System.out.println("-random iterator");
        for (String s : rq) {
            System.out.println(s);
        }

        System.out.println("-random iterator one more time");
        for (String s : rq) {
            System.out.println(s);
        }
    }
}
