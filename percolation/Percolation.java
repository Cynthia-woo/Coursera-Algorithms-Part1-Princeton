/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[][] grid;
    int count = 0;
    int top;
    int bottom;
    WeightedQuickUnionUF uf; // percolation
    WeightedQuickUnionUF uf2; // if full
    int len;

    // creates n-by-n grid, with all sites initially blocked(0)
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Given n <= 0");
        }
        grid = new boolean[n][n];
        top = n * n;
        bottom = n * n + 1;
        uf = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
        len = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (checkIndex(row, col)) {
            if (!isOpen(row, col)) {
                grid[row - 1][col - 1] = true;
                count++;
            }
            if (row == 1) {// first row
                uf.union(col - 1, top);
                uf2.union(col - 1, top);
            }
            if (row == len) {// last row
                uf.union((row - 1) * len + col - 1, bottom);
            }
            if (row > 1 && isOpen(row - 1, col)) {// top is open
                uf.union((row - 1) * len + col - 1, (row - 2) * len + col - 1);
                uf2.union((row - 1) * len + col - 1, (row - 2) * len + col - 1);
            }
            if (row < len && isOpen(row + 1, col)) {// bottom is open
                uf.union((row - 1) * len + col - 1, row * len + col - 1);
                uf2.union((row - 1) * len + col - 1, row * len + col - 1);
            }
            if (col > 1 && isOpen(row, col - 1)) {// left is open
                uf.union((row - 1) * len + col - 1, (row - 1) * len + col - 2);
                uf2.union((row - 1) * len + col - 1, (row - 1) * len + col - 2);
            }
            if (col < len && isOpen(row, col + 1)) {// right is open
                uf.union((row - 1) * len + col - 1, (row - 1) * len + col);
                uf2.union((row - 1) * len + col - 1, (row - 1) * len + col);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (checkIndex(row, col)) {
            return grid[row - 1][col - 1];
        }
        throw new IllegalArgumentException();
    }

    // is the site (row, col) full? Checks if the site (i, j) is connected to the top (i.e., water reaches the site).
    public boolean isFull(int row, int col) {
        if (checkIndex(row, col)) {
            // return uf2.connected((row - 1) * len + col - 1, top);// depracated
            return uf2.find((row - 1) * len + col - 1) == uf2.find(top);
        }
        throw new IllegalArgumentException();
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

    private boolean checkIndex(int i, int j) {
        if (i < 1 || i > len || j < 1 || j > len) return false;
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
