/**
 * Counting inversions.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 * Eg: In this array: [2, 4, 1, 3, 5], there are three inversions: 2,1; 4,1; 4,3
 *
 * Inversion Count for an array indicates – how far (or close) the array is from being sorted.
 * If array is already sorted then inversion count is 0.
 * If array is sorted in reverse order that inversion count is the maximum.
 */

public class CountingInversions {
    private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // copy
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        // compare and merge
        int i = lo, j = mid + 1;
        int count = 0;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) <= 0) a[k] = aux[i++];
            else {
                a[k] = aux[j++];

                //This is the most important part to count the inversions.
                //If aux[i] > aux[j], then following items in left part are all > aux[j].
                //So for aux[j], there are totally (mid - i + 1) reversed items: aux[i], aux[i + 1], ..., aux[mid] > aux[j],
                //since at this stage, sub-array [lo, mid], [mid + 1, hi] are sorted repectively.
                count += mid - i + 1;
            }
        }
        return count;
    }

    private static int sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int count1 = sort(a, aux, lo, mid);
        int count2 = sort(a, aux, mid + 1, hi);
        // count between left part and right part
        int count3 = merge(a, aux, lo, mid, hi);
        return count1 + count2 + count3;
    }

    private static int sortCount(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        return sort(a, aux, 0, n - 1);
    }

    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        CountingInversions c = new CountingInversions();
        System.out.println(c.sortCount(a));
    }
}
