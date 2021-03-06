package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/1/8
 * @description 189. 旋转数组
 */
public class Rotate {

    /**
     * 方法一：使用额外的数组
     *
     * @param nums
     * @param k
     */
    public static void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; ++i) {
            newArr[(i + k) % n] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, n);
    }

    /**
     * 方法二：环状替换
     *
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }

    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    /**
     * 方法三：数组翻转
     *
     * @param nums
     * @param k
     */
    public static void rotate3(int[] nums, int k) {
        k %= nums.length;
        //全部反转一遍
        reverse(nums, 0, nums.length - 1);
        //0 到 k-1 反转
        reverse(nums, 0, k - 1);
        //k 到 最后反转
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate2(nums, 3);
        int[] nums1 = {99,-1,-100,3};
        rotate1(nums1,2);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(nums1));
    }


}
