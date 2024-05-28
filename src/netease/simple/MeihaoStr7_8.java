package netease.simple;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 7-8 美好字符串
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class MeihaoStr7_8 {

    /**
     * 解题思路： 暴力枚举（子字符串） + 二进制标记，时间复杂度O(n^2)
     */
    /**
     * 通过“二进制标记法”，来判断一个字符串是否是漂亮字符
     */
    public static boolean isBeautyStr(String str) {
        // 因为总共只有26个字母，所以可以采用二进制来进行标记
        // lower数组中存放出现过的小写字母，upper数组中存放出现过的大些字母
        // 当lower于upper数组相同时，则说明该字符串为漂亮字符串
        String[] lowerArr = new String[26];
        String[] upperArr = new String[26];
        char lower = 'a';
        char upper = 'A';
        // 初始化lowerArr、upperArr
        for (int i = 0; i < 26; i++) {
            lowerArr[i] = "0";
            upperArr[i] = "0";
        }
        // 在对应字母出现过的位置置1
        for (int i = 0; i < 26; i++) {
            String l = new String(new char[]{(char) ((int) lower + i)});
            if (str.contains(l)) {
                lowerArr[i] = "1";
            }
            String u = new String(new char[]{(char) ((int) upper + i)});
            if (str.contains(u)) {
                upperArr[i] = "1";
            }
        }
        String lowerStr = String.join(",", lowerArr);
        String upperStr = String.join(",", upperArr);
        return lowerStr.equals(upperStr);
    }

    public static boolean isBeautyStr1(String str) {
        String[] lower = new String[26];
        Arrays.fill(lower, "0");
        String[] upper = new String[26];
        Arrays.fill(upper, "0");
        for(int i = 0; i< str.length(); i++){
            char c = str.charAt(i);
            if(c >= 'a' && c <= 'z'){
                int x = (int)c - (int)'a';
                lower[x] = "1";
            }
            if(c >= 'A' && c <= 'Z'){
                int x = (int)c - (int)'A';
                upper[x] = "1";
            }
        }
        return String.join(",",lower).equals(String.join(",",upper));

    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String inputStr = in.nextLine();
        String longestBeautyStr = "";
        for (int i = 0; i < inputStr.length(); i++) {
            // 下面这块截取容易写错
            for (int j = i + 1; j <= inputStr.length(); j++) {
                String tmp = inputStr.substring(i, j);
                if (isBeautyStr(tmp) && tmp.length() > longestBeautyStr.length()) {
                    longestBeautyStr = tmp;
                }
            }
        }
        System.out.println(longestBeautyStr);
    }

}
