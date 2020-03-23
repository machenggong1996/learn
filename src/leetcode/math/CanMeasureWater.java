package leetcode.math;

/**
 * Created by machenggong on 2020/3/21.
 */
public class CanMeasureWater {

    /**
     * 水壶问题
     *
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        return z == 0 || (x + y >= z && z % gcd(x, y) == 0);
    }

    static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4));
    }

}
