package suanfa2024;

/**
 * x的平方根
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
            if ((long) mid * mid <= x) {
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

    public static void main(String[] args) {
        System.out.println(mySqrt2(8));
    }


}
