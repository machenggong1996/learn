package leetcode.string;

/**
 * @author machenggong
 * @date 2020/08/19
 */
public class CountSubstrings {

    /**
     * 回文子串数量
     *
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
    }

}
