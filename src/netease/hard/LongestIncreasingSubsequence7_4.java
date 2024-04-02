package netease.hard;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/3/12
 */
public class LongestIncreasingSubsequence7_4 {

    public static void main(String[] args) {
        // 10 9 2 5 3 7 101 18
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        int[] nums = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
        int length = lengthOfLIS(nums);
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

}
