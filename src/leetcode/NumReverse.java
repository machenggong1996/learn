package leetcode;

/**
 * https://leetcode.cn/problems/reverse-integer/
 * 整数反转
 *
 * @author machenggong
 * @since 2024/12/8
 */
public class NumReverse {

    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483644));
    }

}
