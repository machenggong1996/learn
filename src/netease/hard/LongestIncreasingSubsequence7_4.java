package netease.hard;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 7-4 最长递增子序列
 *
 * @author machenggong
 * @since 2024/3/12
 */
public class LongestIncreasingSubsequence7_4 {

    public static void main(String[] args) {
//        // 10 9 2 5 3 7 101 18
//        Scanner scanner = new Scanner(System.in);
//        String[] input = scanner.nextLine().split(" ");
//        int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        int[] nums = {10,9,2,5,3,7,101,18}; // 4 [2,3,7,101]
        int length = lengthOfLIS1(nums);
        System.out.println(length);
    }

    /**
     * 10 9 2 5 1 7 101 18
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            // a 要搜索的数组
            // fromIndex 指定范围开始索引（包含）
            // toIndex 指定范围结束索引（不包含）
            // key 要搜索的值
            // fromIndex=0 toIndex=len 比dp[0]小的数= -(fromIndex + 1) = -1, 比dp[len]大的数= -(toIndex + 1) = -(len+1)
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }

    public static int lengthOfLIS1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

}
