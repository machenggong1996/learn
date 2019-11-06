package leetcode;

/**
 * Created by mache on 2019/11/5.
 */
public class StringToInteger {

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) return 0;
        str = str.trim();
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + str.charAt(i) - '0';
            if (sign == 1 && res > Integer.MAX_VALUE)return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }
        return (int) res;
    }
    public static void main(String[] args) {
        System.out.println(myAtoi("123"));
    }


}
