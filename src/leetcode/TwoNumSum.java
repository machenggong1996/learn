package leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by machenggong on 2020/3/8.
 */
public class TwoNumSum {

    /**
     * leetcode 两数之和
     */

    public static int[] twoSum(int[] nums, int target) {
        int[] a = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                    //return new int[] {i,j};
                }
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] s = {2, 7, 11, 15};
        int[] r = twoNumMap(s, 26);
        System.out.println(Arrays.toString(r));
    }

    public static int[] twoNumMap(int[] nums, int target) {
        int[] a = new int[2];

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) == null) {
                map.put(nums[i],i);
            }else {
                a[1] = map.get(target - nums[i]);
                a[0] = i;
            }
        }
        return a;
    }


}
