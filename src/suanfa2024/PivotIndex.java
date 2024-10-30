package suanfa2024;

import java.util.Arrays;

/**
 * LCR 012. 寻找数组的中心下标
 * https://leetcode.cn/problems/tvdfij/solutions/1038043/zuo-you-liang-bian-zi-shu-zu-de-he-xiang-5j4r/
 *
 * @author machenggong
 * @since 2024/10/10
 */
public class PivotIndex {

    // 前缀和
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

    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
    }

}
