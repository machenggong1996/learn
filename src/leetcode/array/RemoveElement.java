package leetcode.array;

/**
 * Created by machenggong on 2020/3/17.
 */
public class RemoveElement {
    /**
     * 移除重复元素
     *
     * @param nums
     * @param val
     * @return
     */

    public static int removeElement(int[] nums, int val) {

        int i = 0;
        int l = nums.length;
        while (i < l) {
            if (nums[i] != val) {
                i++;
            } else {
                int j = i;
                while (j < nums.length - 1) {
                    nums[j] = nums[j + 1];
                    j++;
                }
                l--;
                nums[nums.length - 1] = nums[i];
            }
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{3, 2, 2, 3}, 3));
    }

}
