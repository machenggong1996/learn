package netease.simple;

import java.util.Scanner;

/**
 * 最少硬币个数
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class CoinCount7_9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int money = Integer.parseInt(scanner.nextLine());
        if (money == 0) {
            System.out.println("-1");
            return;
        }
        int fiveCount = money / 5;
        int twoCount = (money % 5) / 2;
        int oneCount = (money % 5) % 2;

        System.out.println(fiveCount + twoCount + oneCount);
    }

}
