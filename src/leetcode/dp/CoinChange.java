package leetcode.dp;

/**
 * @author machenggong
 * @since 2021/6/10
 */
public class CoinChange {

    /**
     * 518 零钱兑换 II
     *
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        //int[] coins = {1, 2, 5};
        int[] coins = {1, 2};
        System.out.println(change(5, coins));
    }
}
