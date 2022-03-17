package leetcode.offer;

/**
 * Created by machengong on 2020/3/24.
 */
public class MyPow {

    /**
     * 数值的n次方
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
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

    public double myPow1(double x, int n) {
        double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x) {
            if (i % 2 != 0) {
                //i是奇数
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
    }

    /**
     * 会超出时间限制
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        double result = 1.0;
        int ns = Math.abs(n);
        for (int i = 0; i < ns; i++) {
            result = result * x;
        }
        return n < 0 ? 1.0 / result : result;
    }

    /**
     * 快速幂
     * https://baike.baidu.com/item/%E5%BF%AB%E9%80%9F%E5%B9%82/5500243?fr=aladdin
     * @param x
     * @param n
     * @return
     */
    public double myPow3(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        double res = 1.0;
        // 指数是负数的情况下
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            // 进行二进制拆分
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow1(2.00000, -2));
        System.out.println(myPow.myPow2(2.00000, -2));
        System.out.println(myPow.myPow3(2.00000, 7));
        System.out.println(Math.pow(2,-2));
    }
}
