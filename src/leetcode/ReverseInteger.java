package leetcode;

/**
 * Created by mache on 2019/11/5.
 */
public class ReverseInteger {

    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return y;
    }

    /**
     * 整数方赞防止int移除
     * @param x
     * @return
     */
    public static int reverse1(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse1(-1234));
    }


    public static int reverseTest(int x) {

        //int 369

        int t = 0;
        int y = 0;
        int a = x;
        while (a != 0) {
            t = a % 10;
            y = y * 10 + t;
            a = a / 10;
        }


        return y;
    }
}
