package netease.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * 7-12 超级回文数
 * https://leetcode.cn/problems/super-palindromes/solutions/1/chao-ji-hui-wen-shu-by-leetcode/
 *
 * @author machenggong
 * @since 2024/3/5
 */
public class SuperPalindrome7_12 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String[] parts = sc.nextLine().split(",");
            long L = Long.parseLong(parts[0]);
            long R = Long.parseLong(parts[1]);

            String results = solve2(L, R);
            System.out.println(results);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    public static String solve2(long L, long R) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (long i = 0; i < R; i++) {
            long i2 = i * i;
            if (i2 < L) continue;
            if (i2 > R) break;
            if (check(i) && check(i2)) {
                sb.append(i2 + ", ");
            }
        }
        String result = sb.toString();
        result = result.substring(0, result.length() - 2);
        result = result + "]";
        return result;
    }

    public static List<Long> solve1(long L, long R) {
        List<Long> resultList = new ArrayList<>();
        for (long i = L; i <= R; i++) {
            if (check(i)) {
                double squareRoot = Math.sqrt(i);
                double floor = Math.floor(squareRoot);
                if (floor == squareRoot) {
                    if (check((long) floor)) {
                        resultList.add(i);
                    }
                }
            }

        }
        return resultList;
    }


    static TreeSet<Long> solve(long L, long R) {
        // System.out.println("L="+L+", R="+R);

        // System.out.println(check(1));
        // System.out.println(check(12321));
        // System.out.println(check(123321));
        // System.out.println(check(123421));

        TreeSet<Long> results = new TreeSet<>();
        // 以下为长度等于1位的超级回文数
        if (L <= 1 && 1 <= R) {
            results.add(1L);
        }

        if (L <= 4 && 4 <= R) {
            results.add(4L);
        }
        if (L <= 9 && 9 <= R) {
            results.add(9L);
        }

        // 以下为长度大于1位的超级回文数
        // 以 i 生成回文数 A，A的平方是超级回文数 B
        for (int i = 1; i <= 99999; i++) { // FIXME 改为 99999
            String s = Integer.toString(i);

            // 生成回文数A1，含有奇数位
            // 其中，中间的数字为j
            for (int j = 0; j <= 9; j++) {
                long A1 = i * 10 + j;
                for (int idx = s.length() - 1; idx >= 0; idx--) { // s 的反序
                    A1 = A1 * 10 + (s.charAt(idx) - '0');
                }

                long B1 = A1 * A1;
                if (check(B1)) {
                    // System.out.println("B1="+B1);
                    if (L <= B1 && B1 <= R) {
                        results.add(B1);
                    }
                }
            }

            // 生成回文数B2，含有偶数位
            long A2 = i;
            for (int idx = s.length() - 1; idx >= 0; idx--) { // s 的反序
                A2 = A2 * 10 + (s.charAt(idx) - '0');
            }

            long B2 = A2 * A2;
            if (check(B2)) {
                // System.out.println("B2="+B2);
                if (L <= B2 && B2 <= R) {
                    results.add(B2);
                }
            }
        }

        return results;
    }

    // 是不是回文数
    static boolean check(long n) {
        String s = Long.toString(n);
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

}
