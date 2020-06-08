package leetcode;

import java.math.BigDecimal;

/**
 * @author machenggong
 * @date 2020/06/03
 */
public class New21Game {

    /**
     *
     * @param N 分数不超过 N
     * @param K 获得不少于 K 分
     * @param W 数字 1-W
     * @return
     */
    public static double new21Game(int N, int K, int W) {
        double[] dp = new double[N + 1];
        double sum = 0;
        dp[0] = 1;
        if (K > 0) {
            sum += 1;
        }
        for (int i = 1; i <= N; i++) {
            dp[i] = sum / W;
            if (i < K) {
                sum += dp[i];
            }
            if (i >= W) {
                sum -= dp[i - W];
            }
        }
        double ans = 0;
        for (int i = K; i <= N; i++) {
            ans += dp[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new21Game(10, 2, 10));
    }

}
