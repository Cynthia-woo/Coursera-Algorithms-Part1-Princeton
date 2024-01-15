/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int count;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf; // percolation
    private WeightedQuickUnionUF uf2; // if full
    private int len;

    // creates n-by-n grid, with all sites initially blocked(0)
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }
        len = n;
        grid = new boolean[n][n];
        top = n * n;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            count++;
        }
        else {
            return;
        }
        if (row == 1) {
            uf.union(col - 1, top);
            uf2.union(col - 1, top);
        }
        if (row == len) {
            uf.union((row - 1) * len + col - 1, bottom);
        }


        // top is open
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union((row - 1) * len + col - 1, (row - 2) * len + col - 1);
            uf2.union((row - 1) * len + col - 1, (row - 2) * len + col - 1);
        }
        // bottom is open
        if (row < len && isOpen(row + 1, col)) {
            uf.union((row - 1) * len + col - 1, row * len + col - 1);
            uf2.union((row - 1) * len + col - 1, row * len + col - 1);
        }
        // left is open
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union((row - 1) * len + col - 1, (row - 1) * len + col - 2);
            uf2.union((row - 1) * len + col - 1, (row - 1) * len + col - 2);
        }
        // right is open
        if (col < len && isOpen(row, col + 1)) {
            uf.union((row - 1) * len + col - 1, (row - 1) * len + col);
            uf2.union((row - 1) * len + col - 1, (row - 1) * len + col);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) throw new IllegalArgumentException();
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full? Checks if the site (i, j) is connected to the top (i.e., water reaches the site).
    public boolean isFull(int row, int col) {
        if (row < 1 || row > len || col < 1 || col > len) throw new IllegalArgumentException();
        // return uf2.connected((row - 1) * len + col - 1, top);// deprecated
        return uf2.find((row - 1) * len + col - 1) == uf2.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        // return uf.connected(top, bottom); // deprecated
        return uf.find(top) == uf.find(bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        int numOfOpens;
        int n = Integer.parseInt(args[0]);
        Percolation pc = new Percolation(n);
        while (!pc.percolates()) {
            int i = StdRandom.uniformInt(1, n + 1);
            int j = StdRandom.uniformInt(1, n + 1);
            if (!pc.isOpen(i, j)) {
                pc.open(i, j);
            }
        }
        numOfOpens = pc.numberOfOpenSites();
        StdOut.println((double) numOfOpens / (n * n));
    }
}
