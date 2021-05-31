package leetcode.math;

/**
 * @author machenggong
 * @since 2021/5/31
 */
public class IsPowerOfFour {

    /**
     * 342. 4的幂
     * @param n
     * @return
     */
    public static boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }


    public static boolean isPowerOfFour1(int n) {
        return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFour(2));
        System.out.println(isPowerOfFour1(64));
    }

}
