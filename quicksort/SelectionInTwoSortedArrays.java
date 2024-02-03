/**
 * Question 2
 * Selection in two sorted arrays.
 * Given two sorted arrays a[] and b[], of sizes N1 and N2, respectively,
 * design an algorithm to find the kth largest key.
 * The order of growth of the worst case running time of your algorithm should be logN, where N=N1+N2.
 *
 * Version 1: N1=N2 and k=N/2
 * Version 2: k=N/2
 * Version 3: no restrictions
 **/

public class SelectionInTwoSortedArrays {

    static int MAX = Integer.MAX_VALUE;
    static int MIN = Integer.MIN_VALUE;

    /**
     *
     * @param a: 两个已排序的数组
     * @param b: 两个已排序的数组
     * @param ah: 起始搜索索引
     * @param bh: 起始搜索索引
     * @param k: 查找的元素的顺序
     * @return 第k大的元素
     */
    private static int select(int[] a, int[] b, int ah, int bh, int k) {
        // 表示从当前搜索起点到数组末尾的元素数量
        int len1 = a.length - ah;
        int len2 = b.length - bh;

        // 在数组a和b中的搜索索引
        int i = ah + (k - 1) * len1 / (len1 + len2);
        int j = bh + (k - 1) * len2 / (len1 + len2);

        // 如果索引超出数组范围，则分别赋值为MAX和MAX
        int ai = i == len1 ? MAX : a[i];
        int bj = j == len2 ? MAX : b[j];
        int ai1 = i == 0 ? MIN : a[i - 1];
        int bj1 = j == 0 ? MIN : b[j - 1];

        //
        if (ai > bj1 && ai < bj) return ai;
        else if (bj > ai1 && bj < ai) return bj;
        // ai太小，我们需要在a数组中向右移动，同时调整k的值
        else if (ai < bj1) return select(a, b, i + 1, bh, k - 1 - i);
        // bj小于ai1，这意味着bj太小，我们需要在b数组中向右移动，同时调整k的值
        else return select(a, b, ah, j + 2, k - 1 - j);
    }


    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9}; // 示例数组a
        int[] b = {2, 4, 6, 8, 10}; // 示例数组b
        int k = 5; // 假设我们想找到第k大的元素，这里k=5表示中位数

        // 调用select函数，ah和bh初始值设为0，表示从数组的起始位置开始
        int result = select(a, b, 0, 0, k);

        System.out.println("第" + k + "大的元素是：" + result);
    }
}
