package leetcode.dp;

/**
 * @author machenggong
 * @date 2020/07/01
 */
public class FindLength {

    public static int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 2, 1 };
        int[] b = new int[] { 3, 2, 1, 4, 7 };
        System.out.println(findLength(a, b));
    }

}
