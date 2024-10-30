package suanfa2024;

/**
 * 最长连续递增序列
 * https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 * @author machenggong
 * @since 2024/10/16
 */
public class FindLengthOfLCIS {

    // 贪心算法
    public static int findLengthOfLCIS(int[] nums) {
        int ans = 0;
        int n = nums.length;
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                // 遇见变小的数字就重新开始计数 相等的数字也一样
                start = i;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println(findLengthOfLCIS(nums));
        int[] nums1 = {2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS(nums1));
    }

}
