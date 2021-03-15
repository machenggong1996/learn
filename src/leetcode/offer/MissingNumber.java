package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class MissingNumber {

    /**
     * 1至n-1缺失的数字
     * 二分查找
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    public static int missingNumber1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 6, 7, 9};
        System.out.println(missingNumber1(nums));
        System.out.println(missingNumber(nums));
    }


}
