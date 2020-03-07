package bishi;

import java.util.Scanner;

/**
 * Created by machenggong on 2020/3/6.
 */
public class Coin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] dp = new int[n + 1][m + 1];
            int[] data = {1, 2, 5, 10};
            dp[0][0] = 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = data[i]; k <= m; k++) {
                        dp[j][k] += dp[j - 1][k - data[i]];
                    }
                }
            }
            System.out.println(dp[n][m]);
        }
    }

}
