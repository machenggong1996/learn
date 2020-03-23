package leetcode.array;

import java.util.Arrays;

/**
 * Created by machenggong on 2020/3/22.
 */
public class MinIncrementForUnique {

    /**
     * 使数组唯一的最小增量
     *
     * @param A
     * @return
     */
    public static int minIncrementForUnique(int[] A) {
        // 先排序
        Arrays.sort(A);
        int move = 0;
        // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数+1
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;
    }

    public static void main(String[] args) {
        System.out.println(minIncrementForUnique(new int[]{1, 2, 2}));
    }
}
