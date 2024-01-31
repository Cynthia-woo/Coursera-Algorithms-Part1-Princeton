/**
 * Shuffling a linked list.
 * Given a singly-linked list containing N items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory and run
 * in time proportional to NlogN in the worst case.
 */

import stdlib.StdRandom;

public class Shuffle {
    private class Node {
        Object item;
        Node next;
    }

    private static void merge(Node lh, Node rh) {
        // use dummy node to test the edge case
        Node dummy = new Node();
        Node curr = dummy;

        while (right != null || left != null) {
            if (left == null) {
                curr.next = right;
                right = right.next;
            } else if (right == null) {
                curr.next = left;
                left = left.next;
            } else if (StdRandom.uniform(2) > 0) {
                curr.next = right;
                right = right.next;
            } else {
                curr.next = left;
                left = left.next;
            }
        }
    }

    private static void shuffle(Node head, int n) {
        if (n == 1) return;
        int k = 1;
        Node mid = head;
        while (k < n / 2){
            mid = mid.next;
        }
        Node rh = mid.next;
        mid.next = null;// split from the bottom => into two separate list
        shuffle(head, n / 2);
        shuffle(rh, n - n / 2);
        merge(head, rh);
        k++;
    }

    public static void main(String[] args) {

    }
}
