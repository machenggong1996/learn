package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class SearchNumCount {

    /**
     * 搜索目标值出现次数
     * 二分查找 找目标值两边的边界位置
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    static int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int m = (i + j) / 2;
            if (nums[m] <= tar) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 6, 6, 8, 8, 10};
        System.out.println(search(arr, 8));
    }

}
