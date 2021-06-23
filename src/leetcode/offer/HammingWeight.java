package leetcode.offer;

/**
 * Created by machenggong on 2020/3/17.
 */
public class HammingWeight {

    /**
     * 剑指 Offer 15. 二进制中1的个数
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {

        int count = 0;
        while (n != 0) {
            // 与1进行与运算，计算出最右边是否为1
            count += n & 1;
            // 往右移动一位
            n = n >>> 1;
        }
        return count;
    }

    public static int hammingWeight1(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight1(0b11111111111111111111111111111101));
    }

}
