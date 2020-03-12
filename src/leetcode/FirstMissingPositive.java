package leetcode;

/**
 * Created by machenggong on 2020/3/12.
 */
public class FirstMissingPositive {

    /**
     * 缺失的第一个正数
     * 从1开始加 然后一直找下一个数
     *
     * @param nums
     * @return
     */

    public static int firstMissingPositive(int[] nums) {
        int num = 1;
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == num) {
                num++;
                index = -1;
            }
            if (index == nums.length - 1) {
                break;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int arr[] = new int[]{3, 4, -1, 1, 2, 7};
        System.out.println(firstMissingPositive(arr));
    }


}
