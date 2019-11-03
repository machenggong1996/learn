package leetcode;

/**
 * Created by mache on 2019/11/2.
 */
public class LongestPalindromic {

    /**
     * 最大回文
     */


    /**
     * DP 动态规划
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String res = null;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                /**
                 *  i到j是否是回文
                 *  dp[i + 1][j - 1] 进行动态规划
                 */

                dp[i][j] = s.charAt(i) == s.charAt(j) && ((j - i <= 2) || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    String res = "";

    public String longest2(String s) {
        if (s == null || s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);//回文为奇数情况
            helper(s, i, i + 1);//回文为偶数情况
        }
        return res;
    }

    public void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }

    public static void main(String[] args) {
        LongestPalindromic longestPalindromic = new LongestPalindromic();
        System.out.println(longestPalindromic.longest2("abbasaasa"));
        //System.out.println(longestPalindrome("abbasaasa"));
    }

}
