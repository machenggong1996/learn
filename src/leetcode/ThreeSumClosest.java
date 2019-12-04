package leetcode;

import java.util.Arrays;

/**
 * Created by mache on 2019/12/3.
 */
public class ThreeSumClosest {

    public static int threeSumClosest(int[] nums, int target) {
        int res = nums[0];

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-1, -1, -1, 0, 1, 2}, 5));
    }

}
