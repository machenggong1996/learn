package leetcode;

/**
 * Created by mache on 2019/11/5.
 */
public class ReverseInteger {

    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
            if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;//考虑越界
        }
        return (int) res;
    }

    public static void main(String[] args) {
        System.out.println(reverseTest(-1234));
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
