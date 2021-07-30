package leetcode.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author machenggong
 * @date 2020/12/08
 */
public class IsStraight {

    /**
     * 剑指 Offer 61. 扑克牌中的顺子
     * 1. 无重复
     * 2. 跳过大小王
     * 3. 最大减去最小 小于 5
     *
     * @param nums
     * @return
     */
    public static boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue; // 跳过大小王
            }
            max = Math.max(max, num); // 最大牌
            min = Math.min(min, num); // 最小牌
            if (repeat.contains(num)) {
                return false; // 若有重复，提前返回 false
            }
            repeat.add(num); // 添加此牌至 Set
        }
        return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

    public static void main(String[] args) {
        System.out.println(isStraight(new int[]{1, 2, 0, 4, 5}));
    }

}
