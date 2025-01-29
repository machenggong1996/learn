package leetcode;

/**
 * Created by mache on 2019/11/5.
 */
public class StringToInteger {

    /**
     * 字符串转整数
     *
     * @param str
     * @return
     */

    public static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        int n = str.length();
        int i = 0;
        int res = 0;
        boolean is_negative = false;
        //第一步，跳过前面若干个空格
        while (i < n && str.charAt(i) == ' ') {
            ++i;
        }
        //如果字符串全是空格直接返回
        if (i == n) {
            return 0;
        }
        //第二步，判断正负号
        if (str.charAt(i) == '-') {
            is_negative = true;
        }
        //如果是正负号，还需要将指针i，跳过一位
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            ++i;
        }
        //第三步，循环判断字符是否在 0~9之间
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            //'0'的ASCII码是48，'1'的是49，这么一减就从就可以得到真正的整数值
            int tmp = str.charAt(i) - '0';
            //判断是否大于 最大32位整数
            if (!is_negative && (res > 214748364 || (res == 214748364 && tmp >= 7))) {
                return 2147483647;
            }
            //判断是否小于 最小32位整数
            if (is_negative && (-res < -214748364 || (-res == -214748364 && tmp >= 8))) {
                return -2147483648;
            }
            res = res * 10 + tmp;
            ++i;
        }
        //如果有负号标记则返回负数
        if (is_negative) {
            return -res;
        }
        return res;
    }

    public static int myAtoi2024(String s) {
        char[] c = s.trim().toCharArray();
        if (c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if (c[0] == '-') sign = -1;
        else if (c[0] != '+') i = 0;
        for (int j = i; j < c.length; j++) {
            if (c[j] < '0' || c[j] > '9') break;
            // 2147483647 结尾是7
            if (res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi2024("123"));
        System.out.println( Integer.MAX_VALUE);
        System.out.println( Integer.MIN_VALUE);
    }


}
