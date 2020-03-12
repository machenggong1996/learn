package leetcode;

/**
 * Created by machenggong on 2020/3/12.
 */
public class GcdOfStrings {


    /**
     * 字符串的最大公因子
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String gcdOfStrings(String str1, String str2) {
        // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABAABA", "ABAABAABA"));

        int n = 64;
        int m = 48;
        int sum = n * m;
        int t;
        if (n < m) {
            t = n;
            n = m;
            m = t;
        }
        while (m != 0) {
            t = n % m;
            n = m;
            m = t;
        }
        System.out.println(n + "\n" + sum / n);
    }

}
