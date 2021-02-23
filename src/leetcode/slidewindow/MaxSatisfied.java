package leetcode.slidewindow;

/**
 * @author machenggong
 * @date 2021/2/23
 * @description
 */
public class MaxSatisfied {

    /**
     * 1052. 爱生气的书店老板
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0;
        int n = customers.length;
        //grumpy[i]=0的在这里已经计算过了
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        int increase = 0;
        for (int i = 0; i < X; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = increase;
        //滑动窗口计算为1的 利用grumpy[i]的 0，1特性
        for (int i = X; i < n; i++) {
            increase = increase - customers[i - X] * grumpy[i - X] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }

    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        System.out.println(maxSatisfied(customers, grumpy, X));
    }

}
