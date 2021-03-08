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
        //存储长度为index的时候最大乘积
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 1; i <= n; i++) {
            int max = 0;
            //j<=i/2是因为1*3和3*1是一样的，没必要计算在内，只要计算到1*3和2*2就好了
            //j即是将n从第j处分割
            //Math.max(j*(i-j),j*dp[i-j]) 中 j*(i-j)指的是分割一次后的乘积；j*dp[i-j]指
            //分割一次后，剩余部分继续分割后的最大乘积,前面已经求解过，所以只需要取结果
            //下面综合起来就是，但j取不同时，与前一次j取值后的dp[i]比较，取最大值，直到j遍历完
            //剪了第一段后，剩下(i - j)长度可以剪也可以不剪。如果不剪的话长度乘积即为j * (i - j)；如果剪的话长度乘积即为j * dp[i - j]。取两者最大值max(j * (i - j), j * dp[i - j])
            for (int j = 1; j <= i / 2; j++) {
                max = Math.max(max, Math.max((i - j) * j, j * dp[i - j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

}
