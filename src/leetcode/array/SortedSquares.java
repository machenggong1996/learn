package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/10/16
 */
public class SortedSquares {

    /**
     * 977. 有序数组的平方
     * @param A
     * @return
     */
    public static int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            if (A[i] * A[i] > A[j] * A[j]) {
                ans[pos] = A[i] * A[i];
                ++i;
            } else {
                ans[pos] = A[j] * A[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[] { -4, -1, 0, 3, 10 })));
    }

}
