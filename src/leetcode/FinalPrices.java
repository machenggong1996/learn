package leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author machenggong
 * @since 2022/9/1
 */
public class FinalPrices {

    /**
     * 1475. 商品折扣后的最终价格
     *
     * @param prices
     * @return
     */
    public static int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int discount = 0;
            for (int j = i + 1; j < n; ++j) {
                if (prices[j] <= prices[i]) {
                    discount = prices[j];
                    break;
                }
            }
            ans[i] = prices[i] - discount;
        }
        return ans;
    }

    public static int[] finalPrices1(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() > prices[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? prices[i] : prices[i] - stack.peek();
            stack.push(prices[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {8, 4, 6, 2, 3};
        System.out.println(Arrays.toString(finalPrices1(prices)));
    }


}
