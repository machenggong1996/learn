package leetcode.math;

/**
 * @author machenggong
 * @date 2021/3/4
 * @description
 */
public class ConvertToBase7 {

    /**
     * 进制转换
     *
     * @param num
     * @return
     */
    public static String convertToBase7(int num) {
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        boolean flag = num < 0;
        num = Math.abs(num);
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        if (flag) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    /**
     * 16 进制转换
     * @param num
     * @return
     */
    public static String convertToBase16(int num) {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e'};
        StringBuilder sb = new StringBuilder();
        if (num == 0) {
            return "0";
        }
        boolean flag = num < 0;
        num = Math.abs(num);
        while (num != 0) {
            sb.append(hex[num % 16]);
            num /= 16;
        }
        if (flag) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToBase7(7));
        System.out.println(convertToBase16(32));
        String a = "aa" + "bb";
        System.out.println(a == "aabb");
    }

}
