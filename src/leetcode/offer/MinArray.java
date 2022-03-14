package leetcode.offer;

/**
 * Created by machenggong on 2020/3/16.
 */
public class MinArray {

    /**
     * 剑指 Offer 11. 旋转数组的最小数字
     *
     * @param numbers
     * @return
     */

    public static int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            //low+(high-low)/2 防止int溢出
            int mid = ((r - l) >> 1) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
            } else {
                //去重
                r--;
            }
        }
        return numbers[l];
    }

    public static void main(String[] args) {
        System.out.println(minArray(new int[]{2, 2, 2, 2, 0, 1, 2, 2}));
    }
}
