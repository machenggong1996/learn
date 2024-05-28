package netease.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 7-7 数字拆分求和 TODO 这道题需要好好看看 需要再写一遍
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class NumberSplitCount7_7_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = Integer.parseInt(in.nextLine());
        handle(k);
    }

    public static void handle(int k) {
        int[] countK = new int[k];
        int t = 0;
        for (int i = 0; i < countK.length; i++) {
            if (i == 0) {
                countK[i] = 0;
            } else {
                t = t + i;
                countK[i] = countK[i - 1] + t;
            }

        }

        for (int x = 1; x < k; x++) {
            for (int n = 1; n < k; n++) {
                int r = x * (n + 1) + countK[n];
                if (r == k) {
                    printResult(x, n, countK);
                    break;
                } else if (r > k) {
                    break;
                }
            }
        }

    }

    public static void printResult(int x, int n, int[] countK) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                resultList.add(String.valueOf(x));
            } else {
                // 这里写错了 写成String.valueOf(x)了
                int r = x + countK[i] - countK[i - 1];
                resultList.add(String.valueOf(r));
            }
        }
        System.out.println(String.join(",", resultList));
    }

}
