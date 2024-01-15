/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions - Analysis of Algorithms
 *
 * Question 1: 3-SUM in quadratic time
 *
 * Design an algorithm for the 3-SUM problem that takes time proportional to N2
 * in the worst case. You may assume that you can sort the N integers in time
 * proportional to N2 or better.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ThreeSum {
    public static void main(String[] args) {
        // initialize array
        ArrayList<Integer> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) data.add(sc.nextInt());
            else break;
        }

        // sort array
        Collections.sort(data);

        // compute 3-Sum
        for (int i = 0; i < data.size() - 2; i++) {
            int j = i + 1;
            int k = data.size() - 1;

            while (j < k) {
                int sum = data.get(i) + data.get(j) + data.get(k);
                if (sum == 0) System.out.println(i+":"+data.get(i)+", "+j+":"+data.get(j)+", "+k+":"+data.get(k));
                if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
    }
}
