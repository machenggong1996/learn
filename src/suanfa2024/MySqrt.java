package suanfa2024;

/**
 * x的平方根
 * https://leetcode.cn/problems/sqrtx/solutions/238553/x-de-ping-fang-gen-by-leetcode-solution/
 *
 * @author machenggong
 * @since 2024/10/10
 */
public class MySqrt {

    // 二分查找
    public static int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // 牛顿迭代
    public static int mySqrt2(int x) {
        if(x == 0){
            return 0;
        }
        double sqrt = sqrt(x, x);
        return (int) sqrt;
    }

    public static double sqrt(double i, int x){
        // 找均值
        double res = (i + x/i)/2;
        if(res == i){
            return i;
        }else {
            return sqrt(res, x);
        }

    }

    // 牛顿迭代 https://www.bilibili.com/video/BV1b741197qY
    // https://leetcode.cn/problems/sqrtx/solutions/238553/x-de-ping-fang-gen-by-leetcode-solution/
    public static int mySqrt3(int x) {
        if (x == 0) {
            return 0;
        }
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            // 小于需要的精度
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt3(8));
    }


}
