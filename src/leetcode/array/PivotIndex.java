package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/1/28
 * @description
 */
public class PivotIndex {

    /**
     * 寻找数组的中心索引
     * @param nums
     * @return
     */
    public static int pivotIndex(int[] nums) {
        int total = Arrays.stream(nums).sum();
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (2 * sum + nums[i] == total) {
                return i;
            }
            sum += nums[i];
        }
        return -1;
    }

}
