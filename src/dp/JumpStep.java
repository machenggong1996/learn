package dp;

/**
 * Created by machenggong on 2020/1/16.
 */

/**
 * https://blog.csdn.net/u013132035/article/details/80554136
 */
public class JumpStep {

    //基于递归实现
    public long jumpStep(int n) {
        if (n < 1) { //没有台阶返回0
            return 0;
        }
        if (n == 1) { //一个台阶一种跳法
            return 1;
        }
        if (n == 2) { //两个台阶两种跳法
            return 2;
        }
        return jumpStep(n - 1) + jumpStep(n - 2); //n个台阶f(n-1)+f(n-2)种跳法
    }

    //基于for循环实现 动态规划
    public long jumpStepByFor(int n) {
        int result[] = {0, 1, 2};
        if (n < 3) {
            return result[n];
        }
        long jumpCount1 = 1;
        long jumpCount2 = 2;
        long jumpCountn = 0;
        for (int i = 3; i <= n; ++i) {
            jumpCountn = jumpCount1 + jumpCount2;
            jumpCount1 = jumpCount2;
            jumpCount2 = jumpCountn;
        }
        return jumpCountn;
    }

    public static void main(String[] args) {
        JumpStep jumpStep = new JumpStep();
        System.out.println(jumpStep.jumpStepByFor(4));
    }

}
