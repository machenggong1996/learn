package leetcode.math;

/**
 * @author machenggong
 * @since 2021/5/27
 */
public class HammingDistance {

    /**
     * 461. 汉明距离
     *
     * @param x
     * @param y
     * @return
     */
    public static int hammingDistance(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            ret += s & 1;
            s >>= 1;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(9, 12));
    }

}
