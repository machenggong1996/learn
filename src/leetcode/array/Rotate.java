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

    /**
     * 水平翻转+对角线翻转
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        rotate2(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }


}
