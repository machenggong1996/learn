package leetcode.array;

/**
 * @author machenggong
 * @since 2021/4/6
 */
public class RemoveDuplicatesII {

    /**
     * 删除有序数组中的重复项 II
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        int slow = 2, fast = 2;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
    }


}
