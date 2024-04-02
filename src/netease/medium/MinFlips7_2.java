package netease.medium;

import java.util.Scanner;

/**
 * 7-2 或运算的最小翻转次数
 *
 * @author machenggong
 * @since 2024/2/28
 */
public class MinFlips7_2 {

    public static int minFlips(int a, int b, int c) {
        int count = 0;
        while (a > 0 || b > 0 || c > 0) {
            // 与1做与运算，取出最低位的值
            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;
            if ((bitA | bitB) != bitC) {
                if (bitC == 0) {
                    // A和B每个翻转都算一次次数
                    count += bitA + bitB;
                } else {
                    count += 1;
                }
            }
            // 右移一位 获取下一个最低位
            a >>= 1;
            b >>= 1;
            c >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        // 1,2,3
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split(",");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        int c = Integer.parseInt(numbers[2]);
        System.out.println(minFlips(a, b, c)); // 输出 0
    }

}
