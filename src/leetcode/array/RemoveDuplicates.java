package leetcode.array;

/**
 * Created by machenggong on 2020/3/16.
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {

        /**
         * 剑指 Offer 03. 数组中重复的数字
         * 面试题 02.01. 移除重复节点
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

    public static int removeDuplicates1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates1(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }

}
