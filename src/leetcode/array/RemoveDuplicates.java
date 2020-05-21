package leetcode.array;

/**
 * Created by machenggong on 2020/3/16.
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {

        /**
         * 移除重复的 去重
         */
// 使用双指针
        if (nums == null || nums.length == 1) {
            return nums.length;
        }
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        return i + 1;

    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }

}
