import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by machenggong on 2019/5/14.
 */

/**
 * 回溯法最大k乘积
 */
public class MaxK {

    private static long ans;
    private static Scanner cin;

    static {
        cin = new Scanner(System.in);
    }

    static void work(int cur, int i, int k, long v) {
        //System.out.println("i = " + i + " cur = " + cur + " k = " + k);
        if (i == k) {
            ans = Math.max(ans, v);
            return;
        }
        if (cur == 0) {
            return;
        }
        int MOD = 1;
        while (cur / MOD != 0) {
            work(cur % MOD, i + 1, k, v * (cur / MOD));
            MOD *= 10;
        }
    }

    public static void main(String[] args) {
//        int num, k;
//        System.out.println("请输入数字num和要分成的段数k: ");
//        while (cin.hasNext()) {
//            num = cin.nextInt();
//            k = cin.nextInt();
//            ans = Long.MIN_VALUE;
//            work(num, 0, k, 1L);
//            if (ans == Long.MIN_VALUE) {
//                System.out.println("整数" + num + "不能被分成" + k + "段");
//            } else {
//                System.out.println(num + "的最大" + k + "乘积是: " + ans);
//            }
//            System.out.println("请输入数字num和要分成的段数k: ");
//        }
        Double d3 = Double.valueOf(1.0/3);
        Double d4 = Double.valueOf(2.0/3);
        BigDecimal d5 = new BigDecimal(1);
        BigDecimal d6 = new BigDecimal(2);
        BigDecimal d7 = new BigDecimal(3);
        BigDecimal d2 = new BigDecimal(d3);
        BigDecimal d1 = new BigDecimal(d4);
        BigDecimal d8 = d5.divide(d7);
        BigDecimal d9 = d6.divide(d7);
        System.out.println();

    }
}
