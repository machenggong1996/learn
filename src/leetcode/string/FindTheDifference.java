package leetcode.string;

/**
 * Created by machenggong on 2020/3/18.
 */
public class FindTheDifference {

    /**
     * 找不同 异或 相同异或为0 0^A=A
     *
     * @param s
     * @param t
     * @return
     */

    public static char findTheDifference(String s, String t) {
        char res = t.charAt(t.length() - 1);
        for (int i = 0; i < s.length(); i++) {
            //异或运算
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }

    /**
     * 求和做差
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference1(String s, String t) {
        int as = 0, at = 0;
        for (int i = 0; i < s.length(); ++i) {
            as += s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            at += t.charAt(i);
        }
        return (char) (at - as);
    }

    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd", "abcde"));
        int a = 2;
        int b = 3;
        a=a^b;
        b=a^b;
        a=a^b;
        int c = a ^ a ^ a;
        System.out.println("a=" + a + ",b=" + b + ",c=" + c);
    }

}
