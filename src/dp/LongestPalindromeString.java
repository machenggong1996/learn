package dp;

/**
 * Created by machenggong on 2020/2/16.
 */
public class LongestPalindromeString {

    /**
     * 输入一个字符串，求出其中最长的回文子串。
     * 子串的含义是：在原串中连续出现的字符串片段。
     * 回文的含义是：子串从左向右看和从右向左看是相同的，例如：abba，yyxyy。 在判断时忽略所有标点符号和空格，且忽略大小写，但是输出应保持原样。
     * 输入字符串的长度不超过5000，且占据单独一行。 应该输出最长的回文串。如果有多个，输出起始位置最靠左的一个。
     */

    /**
     * 动态规划算法
     * dp(i, j) 表示是否 s(i ... j) 能够形成一个回文字符串
     * 当 s(i) 等于 s(j) 并且  s(i+1 ... j-1) 是一个回文字符串时 dp(i, j) 的取值为 true
     * 当我们找到一个回文子字符串时，我们检查其是否为最长的回文字符串
     */
    public static String longestPalindrome(String s) {
        // n表示字符串的长度
        int n = s.length();
        String res = null;
        // 定义一个dp数组
        boolean[][] dp = new boolean[n][n];
        // 外层循环控制从最后一个字符向第一个字符渐进
        for (int i = n - 1; i >= 0; i--) {
            // 内层循环控制
            for (int j = i; j < n; j++) {
                // dp数组元素  j - i < 3为三种情况位置一样 位置相差一个 位置隔一个
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

                if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // 读取输入的字符串
        String strdemo = "fafadabcbafdfdfas";
        String ANSWER = longestPalindrome(strdemo);
        System.out.println(ANSWER);
    }

}
