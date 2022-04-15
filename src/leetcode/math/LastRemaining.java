package leetcode.math;

/**
 * @version 1.0
 * @author: machenggong
 * @date: 2020-03-30 9:27
 * @description:
 */
public class LastRemaining {

    /**
     * 约瑟夫环 圆圈中最后剩下的数字 动态规划倒着推
     * f(1) = 0             3
     * f(2) = (f(1)+m)%2=1  1 3       删除1
     * f(3) = (f(2)+m)%3=1  1 3 4     删除4
     * f(4) = (f(3)+m)%4=0  3 4 0 1   删除0
     * f(5) = (f(4)+m)%5=3  0 1 2 3 4 删除2
     *
     * @param n 数字个数
     * @param m 位置
     * @return 最后剩下的数字
     */
    public static int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
        System.out.println(lastRemaining1(5, 1));
    }

    public static int lastRemaining1(int n, int m) {
        return f(n, m);
    }

    public static int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
        return (m + x) % n;
    }

}
