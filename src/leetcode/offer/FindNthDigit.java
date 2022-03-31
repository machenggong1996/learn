package leetcode.offer;

/**
 * @author machenggong
 * @since 2022/3/31
 */
public class FindNthDigit {

    /**
     * 剑指 Offer 44. 数字序列中某一位的数字
     *
     * @param n
     * @return
     */
    public static int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        // 数位数量
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        // (n - 1)/digit 是 第 n是num的第几位
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    public static void main(String[] args) {
        System.out.println(findNthDigit(11));
    }

}
