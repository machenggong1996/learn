package leetcode.offer;

/**
 * Created by machenggong on 2020/3/17.
 */
public class HammingWeight {

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

    public static void main(String[] args) {
        System.out.println(hammingWeight(0b11111111111111111111111111111101));
    }

}
