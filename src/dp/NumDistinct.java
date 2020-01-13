package dp;

import java.util.Arrays;

/**
 * Created by machenggong on 2020/1/13.
 */
public class NumDistinct {

    /**
     * https://blog.csdn.net/qq874455953/article/details/83959093
     * dp[i][j] = dp[i][j-1] +  (s[i] == t[j]) ? dp[i-1][j-1] : 0
     *
     * @param s
     * @param t
     * @return
     */

    public static int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        Arrays.fill(dp[0], 1);

        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (s.charAt(j - 1) == t.charAt(i - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("babgbag", "bag"));

    }

}
