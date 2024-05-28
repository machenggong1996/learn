package netease.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 7-1前缀和
 *
 * @author machenggong
 * @since 2024/2/28
 */
public class CheckSubArraySum7_1 {

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    /**
     * 时间复杂度: O(n)
     * 当一个连续的子数组和 Sum[i~j] 为k的倍数时，那么 Sums[0～j] 和 Sums[0~i] 除以 k 的余数必然相同
     * 此时只需要判断 j-i是否大于等于2就行了
     * Sums[0～j]为满足条件的连续子数组的前缀和，只需要遍历一遍数组，既可以拿到所有的前缀和，时间复杂度为O(n)
     * Sums[0～j] / k 得到的余数，可以存储在一个哈希表中, 依次遍历 前缀和 % k，当出现hash碰撞时，只需要判断两者前缀的下标距离，如果超过2，则认为出现了满足条件的连续子数组
     */
    public static int checkSubArraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // 可以没有
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            // 23,2,4,6,7  23 29 除 6 余 5
            if (map.containsKey(sum)) {
                // 如果两个前缀和的下标距离大于等于2，则返回1
                if (i - map.get(sum) > 1) {
                    return 1;
                }
            } else {
                map.put(sum, i);
            }
        }
        return 0;
    }

//    public static void main(String[] args) {
//        nums = [23,2,4,6,7], k = 6
//        int[] nums = {23, 2, 4, 6, 7};
//        int k = 6;
//        System.out.println(checkSubArraySum(nums, k)); // 输出 true
//    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String input  = in.nextLine();
        String[] numsArr = input.split(",");
        int k = Integer.parseInt(in.nextLine());
        int[] nums = new int[numsArr.length];
        // 初始化nums
        for(int i = 0; i < numsArr.length; i++) {
            nums[i] = Integer.parseInt(numsArr[i]);
        }
        System.out.println(checkSubArraySum(nums, k));
    }

}
