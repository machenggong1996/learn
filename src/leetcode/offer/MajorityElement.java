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

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] { 1, 2, 3, 2, 2, 2, 5, 4, 2 }));
    }

}
