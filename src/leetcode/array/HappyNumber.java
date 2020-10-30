package leetcode.array;

/**
 * @author machenggong
 * @date 2020/04/30
 */
public class HappyNumber {

    /**
     * 快乐数
     * 设置快慢指针
     *
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            slow = squareSum(slow);
            fast = squareSum(fast);
            fast = squareSum(fast);
        } while (slow != fast);
        if (fast == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    private static int squareSum(int m) {
        int squaresum = 0;
        while (m != 0) {
            squaresum += (m % 10) * (m % 10);
            m /= 10;
        }
        return squaresum;
    }

}
