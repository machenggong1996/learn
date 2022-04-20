package leetcode.offer;

/**
 * @author machenggong
 * @since 2022/4/20
 */
public class StrToInt {

    public static int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }
        for (int j = i; j < c.length; j++) {
            // 只考虑第一段连续数字
            if (c[j] < '0' || c[j] > '9') {
                break;
            }
            // 防止数字越界 -2147483648 2147483647
            if (res > bndry || res == bndry && c[j] > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }
    public static int strToInt1(String str) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, length = str.length();
        if(length == 0) {
            return 0;
        }
        while(str.charAt(i) == ' ') {
            if(++i == length) {
                return 0;
            }
        }
        if(str.charAt(i) == '-') {
            sign = -1;
        }
        if(str.charAt(i) == '-' || str.charAt(i) == '+') {
            i++;
        }
        for(int j = i; j < length; j++) {
            if(str.charAt(j) < '0' || str.charAt(j) > '9') {
                break;
            }
            if(res > bndry || res == bndry && str.charAt(j) > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }


    public static void main(String[] args) {
        System.out.println(strToInt("214748365"));
    }


}
