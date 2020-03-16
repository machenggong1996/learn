package leetcode.string;

/**
 * Created by machenggong on 2020/3/16.
 */
public class CompressString {

    /**
     * 字符串压缩
     *
     * @param s
     * @return
     */

    public static String compressString(String s) {

        String res = "";
        int i = 0;

        while (i < s.length()) {
            int c = 1;
            res = res + s.charAt(i);
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                c++;
                i++;
            }
            i++;
            res = res + c;
        }

        if (s.length() < res.length()) {
            res = s;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(compressString("abbccd"));
    }

}
