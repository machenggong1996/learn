package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author machenggong
 * @date 2020/11/27
 */
public class FourSumCount {

    /**
     * 四数相加 使用hash
     * 时间复杂度：O(n^2)O(n
     * 2
     * )。我们使用了两次二重循环，时间复杂度均为 O(n^2)O(n
     * 2
     * )。在循环中对哈希映射进行的修改以及查询操作的期望时间复杂度均为 O(1)O(1)，因此总时间复杂度为 O(n^2)O(n
     * 2
     * )。
     * <p>
     * 空间复杂度：O(n^2)O(n
     * 2
     * )，即为哈希映射需要使用的空间。在最坏的情况下，A[i]+B[j]A[i]+B[j] 的值均不相同，因此值的个数为 n^2n
     * 2
     * ，也就需要 O(n^2)O(n
     * 2
     * ) 的空间。
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>(16);
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = new int[] { 1, 2 };
        int[] B = new int[] { -2, -1 };
        int[] C = new int[] { -1, 2 };
        int[] D = new int[] { 0, 2 };
        System.out.println(fourSumCount(A, B, C, D));
    }

}
