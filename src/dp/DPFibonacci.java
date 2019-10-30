package dp;

/**
 * Created by mache on 2019/10/29.
 */
public class DPFibonacci {

    /**
     * 斐波那契数列递归
     *
     * @param n
     * @return
     */
    public static int solutionFibonacci1(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return solutionFibonacci1(n - 1) + solutionFibonacci1(n - 2);
        }
    }

    /**
     * 斐波那契数列动态规划
     *
     * @param n
     * @return
     */
    public static int solutionFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            int result[] = new int[n + 1];
            result[0] = 0;
            result[1] = 1;
            for (int i = 2; i <= n; i++) {
                result[i] = result[i - 1] + result[i - 2];
            }
            return result[n];
        }
    }

    public static void main(String[] args) {
        System.out.println(solutionFibonacci1(3));
    }
}
