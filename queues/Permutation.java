/******************************************************************************
 *  Name:
 *  Date:
 *  Description:
 *  Subset client. Write a client program Permutation.java
 *  that takes a command-line integer k;
 *  reads in a sequence of N strings from standard input using StdIn.readString();
 *  and prints out exactly k of them, uniformly at random.
 *  Each item from the sequence can be printed out at most once.
 *  You may assume that 0 ≤ k ≤ N, where N is the number of string on standard input.
 *****************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }
    }
}
