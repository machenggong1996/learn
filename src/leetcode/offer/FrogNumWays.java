package leetcode.offer;

/**
 * Created by machenggong on 2020/3/13.
 */
public class FrogNumWays {

    public static int numWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 3; i <= n; i++) {
            int tmp = (dp[1] + dp[0]) % 1000000007;
            ;
            dp[0] = dp[1];
            dp[1] = tmp;
        }
        return dp[1];
    }

    public static void main(String[] args) {
        System.out.println(numWays(3));
    }

}
