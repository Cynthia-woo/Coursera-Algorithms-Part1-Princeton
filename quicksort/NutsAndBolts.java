/******************************************************************************
 *  Compilation:  javac NutsAndBolts.java
 *  Execution:    java NutsAndBolts
 *
 *  Nuts and bolts. A disorganized carpenter has a mixed pile of n nuts and n
 *  bolts. The goal is to find the corresponding pairs of nuts and bolts.
 *  Each nut fits exactly one bolt and each bolt fits exactly one nut.
 *  By fitting a nut and a bolt together, the carpenter can see which one is
 *  bigger (but the carpenter cannot compare two nuts or two bolts directly).
 *  Design an algorithm for the problem that uses at most proportional to
 *  n log n compares (probabilistically).
 *
 ******************************************************************************/


import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;


public class NutsAndBolts implements Iterable<String>{
    private final char[] nuts;
    private final char[] bolts;

    public NutsAndBolts(char[] nuts, char[] bolts) {
        if (nuts == null || bolts == null) throw new IllegalArgumentException();
        this.nuts = nuts.clone();
        this.bolts = bolts.clone();

        matchNutsAndBolts(this.nuts, this.bolts, 0, nuts.length -1);
    }

    private static void matchNutsAndBolts(char[] nuts, char[] bolts, int lo, int hi) {
        if (lo >= hi) return;
        int mid = partition(nuts, lo, hi, bolts[hi]);
        partition(bolts, lo, hi, nuts[mid]);
        matchNutsAndBolts(nuts, bolts, lo, mid - 1);
        matchNutsAndBolts(nuts, bolts, mid + 1, hi);
    }

    private static int partition(char[] a, int lo, int hi, char mid) {
        int i = lo, j = lo;
        while (j < hi) {
            if (a[j] < mid) {
                 exch(a, i++, j);
            } else if (a[j] == mid) {
                exch(a, j--, hi);//将所有等值的元素一道数组的一边，等待下次考虑
            }
            j++;
        }

        return j;
    }

    private static void exch(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int n = 0;

            @Override
            public boolean hasNext() {
                return n < nuts.length;
            }

            @Override
            public String next() {
                return bolts[n] + " -> " + nuts[n++];
            }
        };
    }


    public static void main(String[] args) {
        char[] nuts = new char[] { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i' };
        char[] bolts = new char[] { 'i', 'u', 'y', 't', 'r', 'e', 'w', 'q' };

        NutsAndBolts nb = new NutsAndBolts(nuts, bolts);

        for (String pair : nb) {
            StdOut.println(pair);
        }
    }
}
