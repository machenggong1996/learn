package leetcode.array;

/**
 * @author machenggong
 * @date 2020/04/21
 */
public class NumberOfSubarrays {

    /**
     * 优美子数组个数
     *
     * @param nums 数组
     * @param k    子数组中奇数个数
     * @return
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        // 滑窗
        int res = 0, count = 0;
        int left = 0, right = 0;
        int tmp = 0;
        while (right < nums.length) {
            if (count < k) {
                count += nums[right] & 1;
                right++;
            }
            if (count == k) {
                tmp = 0;
                while (count == k) {
                    ++res;
                    ++tmp;
                    //窗口滑动
                    count -= nums[left] & 1;
                    ++left;
                }
            } else {
                res += tmp;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 1, 2, 1, 1 };
        System.out.println(numberOfSubarrays(arr, 2));
    }
}
