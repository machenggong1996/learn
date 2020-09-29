package bishi;

import java.util.Scanner;

/**
 * Created by machenggong on 2020/3/6.
 */
public class Coin {

    String s = "good";
    Integer i = 10;

    public static void main(String[] args) {

        Coin c = new Coin();
        char[] cs = new char[]{'a','b','c'};
        c.exchange(c.s,cs,c.i);
        System.out.println(c.i);
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] dp = new int[n + 1][m + 1];
            int[] data = {1, 2, 5, 10};
            dp[0][0] = 1;
            for (int i = 0; i < 3; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int k = data[i]; k <= m; k++) {
                        dp[j][k] += dp[j - 1][k - data[i]];
                    }
                }
            }
            System.out.println(dp[n][m]);
        }


    }

    public void exchange(String s,char[] cs,Integer i){
        s = "hello";
        cs[0] = 'a';
        i = 100;
    }

}
