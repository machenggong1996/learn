package leetcode.dp;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/12/17
 * @description
 */
public class MaxProfit {

    /**
     * 买卖股票的最佳时机含手续费
     * 动态规划演进版
     * 状态转移
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        //sell卖出最大利润 buy买入之后手中持有一张股票最大利润
        int sell = 0, buy = -prices[0];
        for (int i = 1; i < n; ++i) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfitDp(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            //dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，
            //dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //第 i 天交易完后手里没有股票的最大利润
        return dp[n - 1][0];
    }

    /**
     * 买卖股票的最佳时机 IV
     *
     * @param k      允许交易的次数
     * @param prices 股票价格
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];

        buy[0][0] = -prices[0];
        sell[0][0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

    public static void main(String[] args) {
        //System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2));
        System.out.println(maxProfit(2,new int[]{1, 3, 2, 8, 4, 9}));
        System.out.println(maxProfit(new int[]{1, 3, 2, 8, 4, 9}));
    }


}
