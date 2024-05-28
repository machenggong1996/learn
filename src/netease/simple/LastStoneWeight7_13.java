package netease.simple;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 7-13 最后一块石头的重量
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class LastStoneWeight7_13 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String[] numbers = input.split(",");
        int[] intArray = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            intArray[i] = Integer.parseInt(numbers[i]);
        }

        int res = lastStoneWeight(intArray);
        System.out.println(res);

    }

    public static int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }
        // 这个条件是pq.size() > 1 不是!isEmpty()
        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

}
