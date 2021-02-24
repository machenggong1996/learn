package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/2/24
 * @description
 */
public class FlipAndInvertImage {

    /**
     * 832. 翻转图像
     *
     * @param A
     * @return
     */
    public static int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                A[i][left] ^= 1;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        System.out.println(Arrays.deepToString(flipAndInvertImage(A)));
    }

}
