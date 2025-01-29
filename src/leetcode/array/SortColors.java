package leetcode.array;

/**
 * 75. 颜色分类
 * https://leetcode.cn/problems/sort-colors/description/
 * @author machenggong
 * @since 2024/12/16
 */
public class SortColors {

    /**
     * 单指针
     * @param nums
     */
    public static void sortColors1(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }

    public static void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            // 使用while 防止交换后i位置的元素是2 交换后还得继续判断
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            // while (i >= p0 && nums[i] == 0) 也可以 因为i>=p0 所以i位置的元素肯定是1 不需要再判断
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        sortColors(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }

}
