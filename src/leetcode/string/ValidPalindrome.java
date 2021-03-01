package leetcode.string;

/**
 * @author machenggong
 * @date 2020/05/19
 */
public class ValidPalindrome {

    /**
     * 680. 验证回文字符串 Ⅱ
     * 允许删除一个字符
     *
     * @param s
     * @return
     */

    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isValid(s, i + 1, j) || isValid(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isValid(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome v = new ValidPalindrome();
        System.out.println(v.validPalindrome("abca"));
    }

}
