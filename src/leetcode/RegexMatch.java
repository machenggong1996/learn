package leetcode;

/**
 * Created by machenggong on 2020/3/10.
 */
public class RegexMatch {

    public static boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen + 1][pLen + 1];
        memory[0][0] = true;
        for (int i = 0; i <= sLen; i++) {
            for (int j = 1; j <= pLen; j++) {
                if (p.charAt(j - 1) == '*') {
                    memory[i][j] = memory[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) ||
                            p.charAt(j - 2) == '.') && memory[i - 1][j]);
                } else {
                    memory[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && memory[i - 1][j - 1];
                }
            }
        }
        return memory[sLen][pLen];
    }

    public static boolean isMatch2024(String s, String p) {
        int m = s.length();
        int n = p.length();
        // 我们用 f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配。
        // 在进行状态转移时，我们考虑 p 的第 j 个字符的匹配情况：
        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    // 为什么 因为j是从1开始的
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        // 完全匹配
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public static void main(String[] args) {
        System.out.println(isMatch2024("a", "a*"));
    }

}
