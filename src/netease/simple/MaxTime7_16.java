package netease.simple;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 7-16 给定数字组成最大时间
 *
 * @author machenggong
 * @since 2024/2/27
 */
public class MaxTime7_16 {

    public static String largestTimeFromDigits(int[] A) {
        Arrays.sort(A);
        for (int i = 3; i >= 0; i--) {  // 第一个小时位，从大到小遍历
            if (A[i] <= 2) {  // 小时的十位不能超过2
                for (int j = 3; j >= 0; j--) {  // 第二个小时位，从大到小遍历
                    // i != j 避免重复 一个数字只能用一次
                    if (i != j && ((A[i] == 2 && A[j] <= 3) || A[i] < 2)) {  // 排除重复和不满足条件的情况  A[i] < 2之前写成了A[j] == 1 这是不行的 还有A[j] == 0
                        for (int k = 3; k >= 0; k--) {  // 第一分钟位，从大到小遍历
                            if (i != k && j != k && A[k] <= 5) {  // 排除重复和分钟位超过5的情况
                                // A[6 - i - j - k] 是第二分钟位, 6是所有索引的和
                                return "" + A[i] + A[j] + ":" + A[k] + A[6 - i - j - k];  // 返回符合条件的时间
                            }
                        }
                    }
                }
            }
        }
        return "";  // 如果没有找到合适的时间，则返回空字符串
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] numbers = input.split(",");
        int[] digits = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            digits[i] = Integer.parseInt(numbers[i]);
        }
        System.out.println(largestTimeFromDigits(digits)); // 输出 19:67
    }

}
