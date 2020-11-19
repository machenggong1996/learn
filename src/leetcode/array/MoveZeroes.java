package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/11/19
 */
public class MoveZeroes {

    /**
     * 移动零
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            }
        }
        if (zeroCount == 0) {
            return;
        }

        for (int i = 0; i < zeroCount; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] == 0) {
                    swap(nums, j, j + 1);
                }
            }
        }

        System.out.println(Arrays.toString(nums));

    }

    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }

    /**
     * 双指针
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 5, 1, 0, 3, 0, 12 };
        moveZeroes2(arr);
    }

}
