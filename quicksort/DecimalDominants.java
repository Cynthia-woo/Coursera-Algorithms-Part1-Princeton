import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * Question 3
 * Decimal dominants.
 * Given an array with N keys, design an algorithm to find all values that occur more than N/10 times.
 * The expected running time of your algorithm should be linear.
 */


public class DecimalDominants {
    private TreeMap<Integer, Integer> counts;
    private int K;
    private int N;
    private int[] A;

    public DecimalDominants(int[] a, int k) {
        counts = new TreeMap<>();
        A = a;
        K = k;
        N = a.length;
    }

    private void buildCounts(int[] a) {
        for (int i = 0; i < N; i++) {
            if (counts.containsKey(a[i])) counts.put(a[i], counts.get(a[i]) + 1);
            else counts.put(a[i], 1);
            if (counts.keySet().size() >= K) removeCounts();// 类似于10进制
        }
    }

    private void removeCounts() {// 十进制
        Iterator<Integer> iterator = counts.keySet().iterator();
        while (iterator.hasNext()) {
            int k = iterator.next();
            int c = counts.get(k);
            if (c > 1) counts.put(k, c - 1);
            else iterator.remove(); // 使用迭代器的remove方法
        }
    }

    public Iterable<Integer> find() {
        List<Integer> result = new LinkedList<>();
        for (int k : counts.keySet()) {
            if (count(k) > N/K) result.add(k);// 验证次数大于N/K（K = 10）
        }
        return result;
    }

    private int count(int k) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == k) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5}; // 示例数组，一些数字出现次数超过N/10
        DecimalDominants dd = new DecimalDominants(a, 10); // 创建DecimalDominants实例，K设置为10
        dd.buildCounts(a); // 构建计数

        Iterable<Integer> result = dd.find(); // 查找出现次数超过N/10的元素

        for (int num : result) {
            System.out.println(num); // 打印结果
        }
    }
}
