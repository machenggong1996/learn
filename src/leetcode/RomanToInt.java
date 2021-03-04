package leetcode;

/**
 * Created by machenggong on 2019/11/27.
 */
public class RomanToInt {

    /**
     * @param s
     * @return
     */

    public static int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = toNumber(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (toNumber(s.charAt(i)) > toNumber(s.charAt(i - 1))) {
                res += toNumber(s.charAt(i)) - 2 * toNumber(s.charAt(i - 1));
            } else {
                res += toNumber(s.charAt(i));
            }
        }
        return res;
    }

    /**
     * 把一个小值放在大值的左边，就是做减法，否则为加法
     * @param s
     * @return
     */
    public static int romanToInt1(String s) {
        int sum = 0;
        int preNum = toNumber(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = toNumber(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt1("IVI"));
    }

    public static int toNumber(char c) {
        int res = 0;
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return res;
    }

}
