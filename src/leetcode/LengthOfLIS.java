package leetcode;

/**
 * Created by machenggong on 2020/3/14.
 */
public class LengthOfLIS {

    public static int lengthOfLIS(int[] nums) {

        /**
         dp[i]: 所有长度为i+1的递增子序列中, 最小的那个序列尾数.
         由定义知dp数组必然是一个递增数组, 可以用 maxL 来表示最长递增子序列的长度.
         对数组进行迭代, 依次判断每个数num将其插入dp数组相应的位置:
         1. num > dp[maxL], 表示num比所有已知递增序列的尾数都大, 将num添加入dp
         数组尾部, 并将最长递增序列长度maxL加1
         2. dp[i-1] < num <= dp[i], 只更新相应的dp[i]

         比前一个数小的数可以取代他
         **/

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j <= i - 1; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }

    public static int lengthOfLISTest(int[] nums) {
        int max = 0;
        if (nums.length == 1) {
            max = 1;
        }
        for (int i = 0; i < nums.length; i++) {
            int t = 0;
            int[] arr = nums;
            for (int j = i; j < arr.length; j++) {

                if (arr[j] > arr[i]) {

                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    t++;
                }

            }

            max = max > t ? max : t;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 3};
        System.out.println(lengthOfLIS(arr));
    }

}
