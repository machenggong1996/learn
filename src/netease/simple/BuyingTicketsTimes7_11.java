package netease.simple;

import java.util.Scanner;

/**
 * 买票需要时间
 * 每人每次买一张票 买完就去排队
 * 第K人 买完要花多长时间
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class BuyingTicketsTimes7_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strArray = scanner.nextLine().split(" ");
        int length = strArray.length;
        int[] tickets = new int[length];
        for (int i = 0; i < length; i++) {
            tickets[i] = Integer.parseInt(strArray[i]);
        }
        int checkResutIndex = Integer.parseInt(scanner.nextLine()); // k
        // 循环排队，其实相当于：
        // 售票员每次向后走一步，问你是来买票的吧？若走到队尾就从头开始，直到卖出去一张票。
        int timeCount = 0;
        int salerIndex = 0;
        // while条件 当checkResutIndex人买完票后，结束循环
        while (tickets[checkResutIndex] != 0) {
            // 售票员：你是来买票的吧？
            // 这个位置没票了 直接下一个
            if (tickets[salerIndex] != 0) {
                // ：来一张
                tickets[salerIndex] = tickets[salerIndex] - 1;
                timeCount++;
                // 亲，记得给五星好评呦~
            }
            // 走到下一个位置，超过队尾就从头开始
            salerIndex = (salerIndex + 1) % length;
        }
        System.out.println(timeCount);
    }

}
