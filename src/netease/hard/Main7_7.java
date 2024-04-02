package netease.hard;

import java.util.Scanner;

/**
 * 不相交的线 思路 最长公共子序列
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Main7_7 {

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                String line1 = in.nextLine();
                String line2 = in.nextLine();

                int[] nums1 = parseNums(line1);
                int[] nums2 = parseNums(line2);

                // System.out.println(Arrays.toString(nums1));
                // System.out.println(Arrays.toString(nums2));

                int count = solve(nums1, nums2);
                System.out.println(count);
            }

        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    static int[] parseNums(String line) {
        String[] parts = line.split(" ");
        int[] nums = new int[parts.length-1];

        // 注：从 parts[1] 开始。parts[0] 是数组长度 第一个数字是数字个数
        for(int i=1; i<parts.length; i++) {
            nums[i-1] = Integer.parseInt(parts[i]);
        }

        return nums;
    }

    /**
     * 不相交的线 思路 最长公共子序列
     * 2 5 1 2 5  10 5 2 1 5 2
     * @param nums1
     * @param nums2
     * @return
     */
    public static int solve(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
        // 链接：https://leetcode.cn/problems/uncrossed-lines/solutions/787955/bu-xiang-jiao-de-xian-by-leetcode-soluti-6tqz/
    }

}
