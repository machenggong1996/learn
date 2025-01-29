package leetcode;

/**
 * Created by machenggong on 2020/3/9.
 */
public class NumIsPalindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome2024(121));
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

    public static boolean isPalindrome2024(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static boolean isPalindrome2024_1(int x) {
        if(x < 0 || (x%10==0 && x != 0)){
            return false;
        }
        int x1 = x;
        int resNum = 0;
        while(x != 0){
            resNum = resNum * 10 + x%10;
            x = x/10;
        }
        return x1 == resNum;
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
