package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/11/10
 */
public class NextPermutation {

    /**
     * 下一个排列
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        //从最后两个开始比较 找到i的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            //从最后的位置j与i进行比较 找到第一个比i大的数（第一次查找i已经确定i之后为由大到小排序）j
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            //交换i与j的位置
            swap(nums, i, j);
        }
        //反转由大到小为由小到大
        reverse(nums, i + 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 4, 5, 2, 6, 3, 1 };
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}
