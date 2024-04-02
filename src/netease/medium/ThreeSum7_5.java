package netease.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/2/28
 */
public class ThreeSum7_5 {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for(int k = 0; k < nums.length - 2; k++) {
            // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(nums[k] > 0) {
                break;
            }
            // 跳过重复值
            if(k > 0 && nums[k] == nums[k-1]) {
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;

            while (i < j) {
                int sum  = nums[k] + nums[i] + nums[j];
                if(sum > 0) {
                    while(i < j && nums[j] == nums[--j]); // 跳过重复值
                } else if(sum < 0) {
                    while(i < j && nums[i] == nums[++i]); // 跳过重复值
                } else {
                    res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while(i < j && nums[i] == nums[++i]); // 跳过重复值
                    while(i < j && nums[j] == nums[--j]); // 跳过重复值
                }
            }
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String[] inputArr = in.nextLine().split(" ");
        int[] nums = new int[inputArr.length];

        // 初始化nums
        for(int i = 0; i < inputArr.length; i++) {
            nums[i] = Integer.parseInt(inputArr[i]);
        }

        List<List<Integer>> res = threeSum(nums);
        System.out.println(res.size());
    }

}
