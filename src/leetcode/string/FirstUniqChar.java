package leetcode.string;

/**
 * @author machenggong
 * @date 2020/12/23
 * @description
 */
public class FirstUniqChar {

    /**
     * 剑指 Offer 50. 第一个只出现一次的字符
     *
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        char[] c = s.toCharArray();
        int[] a = new int[26];
        for (int i = 0; i < c.length; i++) {
            a[c[i] - 'a'] = a[c[i] - 'a'] + 1;
        }
        for (int i = 0; i < c.length; i++) {
            if (a[c[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static char firstUniqChar1(String s) {
        char[] chars = new char[26];
        char[] chars1 = s.toCharArray();
        for (char c : chars1) {
            chars[c - 'a']++;
        }
        for (char c : chars1) {
            if (chars[c - 'a'] == 1) {
                return c;
            }
        }
        return ' ';
    }


    public static void main(String[] args) {
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar1("loveleetcode"));
    }

}
