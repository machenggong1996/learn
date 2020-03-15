package leetcode.offer;

/**
 * Created by machenggong on 2020/3/12.
 */
public class FindRepeatNumber {

    /**
     * 找出数组中重复数字
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {

        int a = 0;

        for (int i = 0; i < nums.length; i++) {
            int t = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (t == nums[j]) {
                    a = nums[j];
                    break;
                }
            }
        }

        return a;
    }

    public static int findRepeatNumber1(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            if (arr[nums[i]] > 1) {
                return nums[i];
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        System.out.println(findRepeatNumber1(arr));
    }

}
