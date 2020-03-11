package leetcode;

/**
 * Created by machenggong on 2020/3/10.
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(str) != 0) {
                str = str.substring(0, str.length() - 1);
            }
        }

        return str;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefixTest(strs));
    }

    public static String longestCommonPrefixTest(String[] strs) {
        String str = "";
        if (strs.length < 2) {
            return null;
        }
        str = strs[0];
        for (int i = 1; i < strs.length; i++) {
            //str在strs[i]中的位置
            while (strs[i].indexOf(str) != 0) {
                str = str.substring(0, str.length() - 1);
            }
        }

        return str;
    }

}
