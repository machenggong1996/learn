package leetcode.offer;

/**
 * Created by machengong on 2020/3/24.
 */
public class MyPow {

    /**
     * 数值的n次方
     *
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        double ans = 1, temp = x;
        int exp = n;
        while (exp != 0) {
            if ((exp % 2) != 0) {
                ans = ans * temp;
            }
            temp = temp * temp;
            exp /= 2;
        }
        return n > 0 ? ans : 1 / ans;
    }

    public static void main(String[] args) {
        System.out.println(myPow(12.0D, 2));
    }
}
