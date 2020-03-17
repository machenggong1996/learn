package leetcode.dp;

/**
 * Created by machenggong on 2020/3/17.
 */
public class CutForMaxProduct3 {

    // A Dynamic Programming solution for Max Product Problem
    //F(n) = max{i * F(n - i), i * (n - i)}, i = 1, 2, ... , n - 1

    /**
     * 剪绳子
     *
     * @param n
     * @return
     */
    public static int maxProd(int n) {
        int val[] = new int[n + 1];
        val[0] = val[1] = 0;

        // Build the table val[] in bottom up manner and return
        // the last entry from the table
        for (int i = 1; i <= n; i++) {
            int max_val = 0;
            for (int j = 1; j <= i / 2; j++) {
                max_val = Math.max(max_val, Math.max((i - j) * j, j * val[i - j]));
            }
            val[i] = max_val;
        }
        return val[n];
    }

    /* Driver program to test above functions */
    public static void main(String[] args) {
        System.out.println("Maximum Product is " + maxProdTest(10));
    }

    public static int maxProdTest(int n) {
        //存储长度为index的时候最大面积
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, Math.max((i - j) * j, j * dp[i - j]));
            }
            dp[i] = max;
        }

        return dp[n];
    }

}
