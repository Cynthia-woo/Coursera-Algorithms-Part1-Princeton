/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions - Stacks and Queues
 * <p>
 * Question 2: Stack with max
 * <p>
 * Create a data structure that efficiently supports the stack operations
 * (push and pop) and also a return-the-maximum operation. Assume the elements
 * are reals numbers so that you can compare them.
 */

import java.util.Stack;
import java.util.TreeSet;

/**
 * Solution:
 * <p>
 * 1) Iterate over stack to find max element. Time: O(n), Space: O(1)
 * 2) Keep an additional list of items in sorted order. Max element is the
 * top of the sorted list. Complex to remove elements on pop. Time: O(1) for
 * max, O(N) for pop, Space: O(n)
 * 3) Keep an additional binary tree of elements. Time: O(lg N), Space: O(N)
 */

class StackWithMax<Item> extends Stack<Item> {
    private TreeSet<Item> tree = new TreeSet<>();
    // TreeSet is implemented as a Red-Black tree, which is a self-balancing
    // binary search tree. This ensures that the elements are always sorted,
    // and operations like insertion, deletion, and retrieval have a time complexity of O(log n).

    public Item max() {
        return tree.last();
    }

    public Item push(Item item) {
        super.push(item);
        tree.add(item);
        return item;
    }

    public Item pop() {
        Item item = super.pop();
        tree.remove(item);
        return item;
    }

    public static void main(String[] args) {
        StackWithMax<Integer> stack = new StackWithMax<>();
        int i = 1;
        int N = 100;

        System.out.println("Size: " + stack.size());
        while (i <= N) {
            if (i % 3 == 0) {
                System.out.println("Max: " + stack.max());
            } else {
                System.out.println("Push: " + i);
                stack.push(i);
            }
            ++i;
        }
        System.out.println("Size: " + stack.size());
        while (!stack.isEmpty()) {
            System.out.println("Pop: " + stack.pop());
        }
        System.out.println("Size: " + stack.size());
    }
}
