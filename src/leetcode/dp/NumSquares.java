package leetcode.dp;

/**
 * @author machenggong
 * @since 2021/6/11
 */
public class NumSquares {

    /**
     * 279. 完全平方数
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }

    public static void main(String[] args) {
        System.out.println(numSquares(4));
    }

}
