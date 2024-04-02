package netease.simple;

import java.util.Scanner;

/**
 * 7-7 数字拆分求和 TODO 这道题需要好好看看
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class NumberSplitCount7_7 {

    /**
     * 设 n 是符合结果序列的第1个数字，i 表示可以拆分成几个数字
     * <p>
     * 则 k = n * i + f(i-1);
     * <p>
     * 其中 f(n) = f(n -1) + (1 + n) * n /2;
     * <p>
     * 则 n = ( k - f(i-1))/ i;
     * <p>
     * i 从2开始遍历，当 n > 0 并且可以整除时，结果符合要求。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int k = Integer.parseInt(input);
        // k=(x+0)+ ~ +(x+0+1+2+ ~ +n)=x(n+1) + [0 + (0+1) + ~ + (0+1+ ~ +n)]
        int[] nCount = new int[k]; //下标为n时，数据=[0 + (0+1) + (0+1+2) + ~ + (0+1+ ~ +n)]
        int t = 0;
        for (int i = 1; i < k; i++) { // 实际上n应该不会走到k这么多的，最大应该不会到k的一半。
            t = t + i;
            nCount[i] = nCount[i - 1] + t;
        }
        // 当x=1的时候，我们遍历n，就能得到最长的n了。后面数字肯定不会需要计算n了
        for (int x = 1; x < k; x++) {
            for (int n = 1; n < k; n++) {
                int currentK = x * (n + 1) + nCount[n];
                if (currentK == k) {
                    printResult(x, n, nCount);
                    break;
                } else if (currentK > k) {
                    break;
                }
            }
        }
    }

    public static void printResult(int x, int n, int[] nCount) {
        StringBuilder sb = new StringBuilder();
        int tmp = x;
        for (int i = 0; i <= n; i++) {
            tmp = tmp + i;
            sb.append(tmp);
            if (i == n) break;
            sb.append(",");
        }
        System.out.println(sb);
    }

}
