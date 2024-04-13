package netease.simple;

import java.util.Scanner;

/**
 * 7-15 编辑距离 动态规划
 *
 * @author machenggong
 * @since 2024/2/27
 */
public class EditDistance7_15 {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] words = in.nextLine().split(" ");
        System.out.println(minDistance(words[0], words[1])); // 输出 3
    }

}
