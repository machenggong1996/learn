package leetcode.array;

/**
 * @author machenggong
 * @since 2021/4/6
 */
public class RemoveDuplicatesII {

    /**
     * 删除有序数组中的重复项 II
     * 出现重复的元素只出现两次
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

    public static int removeDuplicates1(int[] nums) {
        int i = 0;
        for (int num : nums) {
            // i<2 表示前两个元素直接添加, num > nums[i - 2] 表示当前元素和前两个元素不相同 则添加
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3, 3, 3};
        System.out.println(removeDuplicates1(nums));
    }


}
