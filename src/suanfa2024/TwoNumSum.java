package suanfa2024;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author machenggong
 * @since 2024/10/11
 */
public class TwoNumSum {

    // 无序 暴力
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    // 无序 哈希表
    public static int[] twoSum1(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            if (map.containsKey(target - nums[i])){
                ans[0] = map.get(target - nums[i]);
                ans[1] = i;
            }
            map.put(nums[i], i);
        }
        return ans;
    }

    // 升序数组
    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[2];
        int left = 0, right = n - 1;
        while (left < right){
            if (nums[left] + nums[right] == target){
                ans[0] = left;
                ans[1] = right;
                break;
            }else if (nums[left] + nums[right] > target){
                right--;
            }else {
                left++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum2(nums, 9)));
    }

}
