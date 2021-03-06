package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/5
 * @description
 */
public class Add {

    /**
     * 剑指offer 面试题65. 不用加减乘除做加法（位运算，清晰图解）
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        while (b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(add(9, 2));
    }


}
