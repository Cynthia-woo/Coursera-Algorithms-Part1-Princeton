/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Stacks and Queues
 *
 * Question 1: Queue with two stacks
 *
 * Implement a queue with two stacks so that each queue operations takes a
 * constant amortized number of stack operations.
 */

/**
 * Solution:
 *
 * Use one stack for keeping track of elements queued up and another for
 * tracking elements to be dequeued. When the dequeue stack if empty, the
 * elements from the enqueue stack are popped and pushed into the dequeue
 * stack. This operation reverses the order of elements so they are in FIFO.
 *
 * The dequeue stack cannot be populated until depleated, otherwise elements
 * would be out of order.
 */

import java.util.NoSuchElementException;
import java.util.Stack;

class StackQueue<Item> {
    private Stack<Item> input = new Stack<>();
    private Stack<Item> output = new Stack<>();
    public StackQueue() {

    }
    public int size() {
        return input.size() + output.size();
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        input.push(item);
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.pop();
    }
}

public class QueueWithTwoStacks {
    public static void main(String[] args) {
        StackQueue<Integer> sq = new StackQueue<>();
        int i = 0;
        int n = 100;
        System.out.println("Size of queue" + sq.size());
        sq.enqueue(i);
        while(i <= n) {
            if (i % 3 == 0) {
                System.out.println("Dequeue: " + sq.dequeue());
            } else {
                sq.enqueue(i);
                System.out.println("Enqueue: " + i);
            }
            i++;
        }
        System.out.println("Size: " + sq.size());
        while (!sq.isEmpty()) {
            System.out.println("Dequeue: " + sq.dequeue());
        }
        System.out.println("Size: " + sq.size());
    }
}
