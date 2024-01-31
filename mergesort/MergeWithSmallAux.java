import java.util.Arrays;

/**
 * Merging with smaller auxiliary array.
 * Suppose that the subarray a[0] to a[N-1] is sorted and the subarray a[N] to a[2*N-1] is sorted.
 * How can you merge the two subarrays so that a[0] to a[2*N-1] is sorted
 * using an auxiliary array of size N (instead of 2N)?
 */

public class MergeWithSmallAux {
    private static void merge(Comparable[] a, Comparable[] aux, int n) {
        // copy first half to aux[]
        for (int i = 0; i < n; i++) {
            aux[i] = a[i];
        }

        // compare and merge
        int i = 0, j = n;
        for (int k = 0; k < a.length; k++) {
            if (i >= n) { // aux is exhausted
                a[k] = a[j++];
            } else if (j >= a.length) { // second half of a is exhausted
                a[k] = aux[i++];
            } else if (aux[i].compareTo(a[j]) < 0) { // current key in aux[] less than in second half of a[]
                a[k] = aux[i++];
            } else { // current key in second half of a[] less than in aux[]
                a[k] = a[j++];
            }
        }
    }

    public static void main(String[] args) {
        Comparable[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100};
        MergeWithSmallAux m = new MergeWithSmallAux();
        int n = a.length / 2;
        Comparable[] aux = new Comparable[n];
        m.merge(a, aux, n);
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
    }
}
