package leetcode.tanxin;

import java.util.Arrays;

/**
 * @author machenggong
 * @since 2021/7/2
 */
public class MaxIceCream {

    /**
     * 1833. 雪糕的最大数量
     *
     * @param costs
     * @param coins
     * @return
     */
    public static int maxIceCream(int[] costs, int coins) {
        // 从小到大排序 可以保证买的雪糕最多
        Arrays.sort(costs);
        int count = 0;
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            int cost = costs[i];
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] costs = {1, 3, 2, 4, 1};
        int[] costs1 = {1,6,3,1,2,5};
        System.out.println(maxIceCream(costs1, 20));
    }

}
