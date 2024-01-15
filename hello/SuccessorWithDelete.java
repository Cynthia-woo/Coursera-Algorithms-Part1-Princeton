/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

public class SuccessorWithDelete {
    private int[] id;
    private int[] actualId;
    private int[] sz;

    private SuccessorWithDelete(int n) {
        id = new int[n];
        actualId = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            actualId[i] = i;
            sz[i] = 1;
        }
    }

    public void remove(int i) {
        if (i < id.length - 1) union(i, i + 1);
    }

    public int successor(int i) {
        return (i < id.length - 1) ? actualId[root(i)] : -1;
    }

    private int root(int i) {
        if (id[i] != i) {
            id[i] = id[id[i]]; // path compression
            i = id[i];
        }
        return i;
    }

    private void union(int i, int j) {
        int p = root(i);
        int q = root(j);
        if (p == q) return;
        if (sz[q] < sz[p]) {
            id[q] = p;
            sz[p] += sz[q];
        }
        else {
            id[p] = q;
            sz[q] += sz[p];
            actualId[q] = id[p];
        }
    }

}
