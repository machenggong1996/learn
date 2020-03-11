package leetcode;

/**
 * Created by machenggong on 2019/6/27.
 */
public class LongestPalindrome {

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);//字符串长度为奇数
            int len2 = expandAroundCenter(s, i, i + 1);//字符串长度为偶数
            int len = Math.max(len1, len2);
            if (len > end - start) {
                //中心点的两边
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    private static String bruteForceSolution(String s) {
        // 处理特殊情况
        if (s == null || s.length() == 0) {
            return "";
        }
        int maxLen = 1, fromIndex = 0, toIndex = 1;
        char[] chars = s.toCharArray();
        // 遍历所有的子串 i为起止点 j为结束点
        for (int i = 0; i < chars.length; ++i) {
            for (int j = i; j < chars.length; ++j) {
                // j - i + 1 > maxLen可以过滤一部分子串
                if (j - i + 1 > maxLen && isPalindromicString(chars, i, j)) {
                    fromIndex = i;
                    toIndex = j;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(fromIndex, toIndex + 1);
    }

    // 判断指定子串是否为回文串
    private static boolean isPalindromicString(char[] chars, int l, int r) {
        while (l < r) {
            if (chars[l] == chars[r]) {
                ++l;
                --r;
            } else {
                return false;
            }
        }
        return true;
    }

    private static String dynamicProgramming(String s){
        int len = s.length();
        if(len == 0){
            return "";
        }
        char[] sToArray = s.toCharArray();
        boolean[][] mem = new boolean[len][len];
        int maxLen = 1;
        int fromIndex = 0;
        int toIndex = 1;
        // 初始化条件
        for(int i = 0; i < len; ++i){
            mem[i][i] = true;
            if(i + 1 < len && sToArray[i] == sToArray[i + 1]){
                mem[i][i + 1] = true;
                maxLen = 2;
                fromIndex = i;
                toIndex = i + 1 + 1;
            }
        }
        // 执行dp
        for(int i = 2; i < len; ++i){
            for(int j = 0; j < i - 1; ++j){
                mem[j][i] = mem[j + 1][i - 1] && sToArray[j] == sToArray[i];
                if(mem[j][i] && i - j + 1 > maxLen){
                    maxLen = i - j + 1;
                    fromIndex = j;
                    toIndex = i + 1;
                }
            }
        }

        return s.substring(fromIndex, toIndex);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abba"));
        System.out.println(bruteForceSolution("abba"));
        System.out.println(dynamicProgramming("abbaa"));
    }
}
