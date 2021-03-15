package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by machenggong on 2019/12/2.
 */
public class ThreeSum {

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }//重复
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    //while去重复
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < sum) {
                    //和排序有关
                    low++;
                } else {
                    high--;
                }
            }
        }
        return res;
    }

    public static List<List<Integer>> threeSumTest(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //跳过重复值
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1, sum = 0 - nums[i];
            while (low < high) {
                if (nums[low] + nums[high] == sum) {
                    res.add(Arrays.asList(nums[low], nums[high], nums[i]));

                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;

                } else if (nums[low] + nums[high] < sum) {
                    low++;
                } else {
                    high--;
                }

            }

        }

        return res;
    }

    public static List<List<Integer>> threeSumTest1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 去重

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            int sum = -nums[i];

            while (left < right) {

                if (nums[left] + nums[right] == sum) {

                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;

                } else if (nums[left] + nums[right] > sum) {
                    right--;
                } else {
                    left++;
                }

            }
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(threeSumTest1(new int[]{-1, -1, -1, 0, 1, 2}));
    }

}
