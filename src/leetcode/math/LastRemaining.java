package leetcode.math;

/**
 * @version 1.0
 * @author: machenggong
 * @date: 2020-03-30 9:27
 * @description:
 */
public class LastRemaining {

    /**
     * 约瑟夫环 圆圈中最后剩下的数字
     *
     * @param n
     * @param m
     * @return
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
    }

}
