package leetcode.string;

/**
 * @author machenggong
 * @since 2022/8/11
 */
public class Reformat {

    /**
     * 1417. 重新格式化字符串
     *
     * @param s 字符串
     * @return
     */
    public static String reformat(String s) {
        // 数字个数
        int sumDigit = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sumDigit++;
            }
        }
        // 字母个数
        int sumAlpha = s.length() - sumDigit;
        if (Math.abs(sumDigit - sumAlpha) > 1) {
            return "";
        }
        // 个数多的在奇数位置 个数少的在偶数位置
        // flag=true 数字在奇数位 字母在偶数位,flag=false 字母在奇数位 数字在奇数位
        boolean flag = sumDigit > sumAlpha;
        char[] arr = s.toCharArray();
        for (int i = 0, j = 1; i < s.length(); i += 2) {
            if (Character.isDigit(arr[i]) != flag) {
                while (Character.isDigit(arr[j]) != flag) {
                    j += 2;
                }
                swap(arr, i, j);
            }
        }
        return new String(arr);
    }

    public static void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }

    public static void main(String[] args) {
        System.out.println(reformat("covid2019"));
    }

}
