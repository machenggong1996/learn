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
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            //每次折半
            if (i % 2 != 0) {
                //奇数乘一次 最后乘一次
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public static void main(String[] args) {
        String s = "";

        //System.out.println(myPow(5, 7));
    }

}
