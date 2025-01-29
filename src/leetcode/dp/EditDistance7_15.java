package leetcode.dp;

import java.util.Scanner;

/**
 * 7-15 编辑距离 动态规划
 * https://leetcode.cn/problems/edit-distance/
 * @author machenggong
 * @since 2024/2/27
 */
public class EditDistance7_15 {

    /**
     * 插入删除替换
     * D[i][j-1] 为 A 的前 i 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，我们在 A 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为 D[i][j-1] + 1；
     *
     * D[i-1][j] 为 A 的前 i - 1 个字符和 B 的前 j 个字符编辑距离的子问题。即对于 A 的第 i 个字符，我们在 B 的末尾添加了一个相同的字符，那么 D[i][j] 最小可以为 D[i-1][j] + 1；
     *
     * D[i-1][j-1] 为 A 前 i - 1 个字符和 B 的前 j - 1 个字符编辑距离的子问题。即对于 B 的第 j 个字符，我们修改 A 的第 i 个字符使它们相同，那么 D[i][j] 最小可以为 D[i-1][j-1] + 1。特别地，如果 A 的第 i 个字符和 B 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，D[i][j] 最小可以为 D[i-1][j-1]
     *
     * @param word1
     * @param word2
     * @return
     */
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
                    // 两者相同向前看一位
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 1.执行word1插入操作，那么比较 word1[0.·i]和 word2[0 . j- 1]的结果
                    // 2.执行word1删除操作，那么比较word1[0··i-1]和 word2[0 . j]的结果
                    // 3.执行替换操作，等同于同时删去，那么比较word1[0.·i-1]和word2[0 …j- 1]的结果
                    // 对word1的删除等于对word2的插入
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
