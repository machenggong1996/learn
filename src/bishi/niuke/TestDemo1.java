package bishi.niuke;

/**
 * @author machenggong
 * @date 2020/12/29
 * @description
 */
public class TestDemo1 {

    private final int count;

    public static void main(String[] args) {
        TestDemo1 testDemo1 = new TestDemo1(88);
        System.out.println(testDemo1.count);
        int a = 8;
        boolean b = (a != 3 && a != 4 && a != 5 && a != 6);
        System.out.println(b);
        System.out.println(split(7, 2));
    }

    public TestDemo1(int a) {
        count = a;
    }

    static int m = 5;
    static int n = 3;
    static int[] temp = new int[n];

    public static int split(int m, int n) {
        int[][] dp = new  int[n + 1][n + 1]; // 将n划分成不大于m的划分法（整数可以相同）
        int[][] dp2 = new  int[n + 1][n + 1]; // 多个整数不同
        int[][] dp3 = new  int[n + 1][n + 1]; // 将n划分成m个数
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
            dp[0][i] = 0;
            dp2[i][0] = 0;
            dp2[0][i] = 0;
            dp3[i][0] = 0;
            dp3[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    dp[i][j] = dp[j][i];
                    dp2[i][j] = dp[j][i];
                    dp3[i][j] = 1;
                } else if (i < j) {
                    dp[i][j] = dp[i][i];
                    dp2[i][j] = dp[i][i];
                    dp3[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1]+ dp[i - j][j];
                    dp2[i][j] = dp2[i][j - 1]+ dp2[i - j][j - 1];
                    dp3[i][j] = dp3[i - 1][j - 1]+ dp3[i - j][j];
                }
            }
        }
        return dp[n][m];
    }

}
