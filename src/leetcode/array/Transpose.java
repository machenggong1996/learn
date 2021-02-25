package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/2/25
 * @description
 */
public class Transpose {

    /**
     * 867. 转置矩阵
     *
     * @param A
     * @return
     */
    public static int[][] transpose(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] ans = new int[C][R];
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                ans[c][r] = A[r][c];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.deepToString(transpose(A)));
    }


}
