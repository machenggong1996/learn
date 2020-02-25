package leetcode;

/**
 * Created by machenggong on 2020/2/25.
 */
public class FindMin {

    /**
     * 思路：
     * 二分查找
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            //求中位数
            int mid = (start + end) >> 1;
            // 需要与nums[end]比较才能判断出来mid的位置
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                // 存在重复元素时，此题目不重复 可直接使用break
                end = end - 1;
            }
        }
        return nums[start];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{0, 1, 2, 4, 5, 6, 7}));
    }

}
