package leetcode.offer;

/**
 * @author machenggong
 * @date 2020/05/19
 */
public class MajorityElement {

    //数组中出现次数超过一半的数字，摩尔投票算法
    public static int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (res == nums[i]) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                res = nums[i];
                count = 1;
            }
        }
        return res;
    }

    public static int majorityElement1(int[] nums) {
        int candidate = -1;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }
        // 判断是不是超过了一半
        count = 0;
        int length = nums.length;
        for (int num : nums) {
            if (num == candidate) {
                count++;
            }
        }
        return count * 2 > length ? candidate : -1;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement1(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

}
