package leetcode;

/**
 * @author machenggong
 * @date 2020/05/11
 */
public class MyPow {

    /**
     * 快速幂
     * <p>
     * 使用折半计算，每次把n缩小一半，这样n最终会缩小到0，任何数的0次方都为1，
     * 这时候我们再往回乘，如果此时n是偶数，直接把上次递归得到的值算个平方返回
     * 即可，如果是奇数，则还需要乘上个x的值。还有一点需要引起我们的注意的是n
     * 有可能为负数，对于n是负数的情况，我们可以先用其绝对值计算出一个结果再取
     * 其倒数即可。我们让i初始化为n，然后看i是否是2的倍数，是的话x乘以自己，
     * 否则res乘以x，i每次循环缩小一半，直到为0停止循环。最后看n的正负，如果
     * 为负，返回其倒数。
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

    public static double quickMul(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    public static double quickMul1(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        // 每次只算一半 算完之后自己乘自己 奇数还要再乘下x
        double y = quickMul1(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2, 10));
    }

}
