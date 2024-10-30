package suanfa2024;

/**
 * 打家劫舍
 * https://leetcode.cn/problems/house-robber/solutions/263856/da-jia-jie-she-by-leetcode-solution/
 *
 * @author machenggong
 * @since 2024/10/24
 */
public class DajiajiesheHomeRobber {

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
//        偷窃第 k 间房屋，那么就不能偷窃第 k−1 间房屋，偷窃总金额为前 k−2 间房屋的最高总金额与第 k 间房屋的金额之和。
//        不偷窃第 k 间房屋，偷窃总金额为前 k−1 间房屋的最高总金额。
//        dp[i] 表示前 i 间房屋能偷窃到的最高总金额
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public static int rob1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        // 如果有两间房 偷最大的
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    // [2,1,1,2] 这个case是不行的
    public static int rob2(int[] nums) {
        int s1 = 0, s2 = 0;
       for (int i = 0; i < nums.length; i++) {
           if(i%2==0){
               s1 = s1 + nums[i];
           }else {
               s2 = s2 + nums[i];
           }
       }
       return Math.max(s1, s2);
    }

    public static void main(String[] args) {
        int[] arr = {2,1,1,2};
        System.out.println(rob1(arr));
    }

}
