package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/05/18
 */
public class ArrayExchange {

    /**
     * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public static int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 是奇数向右移动
            while (left < right && nums[left] % 2 != 0) {
                left++;
            }
            // 是偶数向左移动
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            // 交换
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(exchange(new int[] { 1, 2, 3, 4, 5 })));
    }

}
