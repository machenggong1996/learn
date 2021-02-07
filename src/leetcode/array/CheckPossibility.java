package leetcode.array;

/**
 * @author machenggong
 * @date 2021/2/7
 * @description
 */
public class CheckPossibility {

    /**
     * 665.非递减数列
     *
     * @param nums
     * @return
     */
    public static boolean checkPossibility(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                nums[i] = y;
                if (isSorted(nums)) {
                    return true;
                }
                nums[i] = x; // 复原
                nums[i + 1] = x;
                return isSorted(nums);
            }
        }
        return true;
    }

    public static boolean isSorted(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            if (nums[i - 1] > nums[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPossibilityI(int[] nums) {
        int n = nums.length, cnt = 0;
        for (int i = 0; i < n - 1; ++i) {
            int x = nums[i], y = nums[i + 1];
            if (x > y) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
                if (i > 0 && y < nums[i - 1]) {
                    nums[i + 1] = x;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 1, 2};
        //int[] nums = new int[]{4,2,3};
        System.out.println(checkPossibilityI(nums));
    }

}
