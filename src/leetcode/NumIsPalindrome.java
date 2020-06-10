package leetcode;

/**
 * Created by machenggong on 2020/3/9.
 */
public class NumIsPalindrome {

    public static void main(String[] args) {
        is(12211);
    }

    /**
     * 判断是不是回文数
     *
     * @param x
     * @return
     */

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rem = 0, y = 0;
        int quo = x;
        while (quo != 0) {
            rem = quo % 10;
            y = y * 10 + rem;
            quo = quo / 10;
        }
        return y == x;
    }

    public static void is(int n) {
        if (n < 0) {
            System.out.println(false);
            return;
        }
        //temp
        int y = 0;
        int q = n;
        while (q != 0) {
            int temp = q % 10;
            y = y * 10 + temp;
            q = q / 10;
        }
        if (y == n) {
            System.out.print(true);
        } else {
            System.out.print(false);
        }
    }


}
