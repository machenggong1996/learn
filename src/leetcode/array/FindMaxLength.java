package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author machenggong
 * @since 2021/6/3
 */
public class FindMaxLength {

    /**
     * 525. 连续数组 前缀和
     *
     * @param nums
     * @return
     */
    public static int findMaxLength(int[] nums) {
        int maxLength = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 1) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(counter, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        System.out.println(findMaxLength(nums));
    }


}
