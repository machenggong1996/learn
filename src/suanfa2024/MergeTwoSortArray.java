package suanfa2024;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * @author machenggong
 * @since 2024/10/15
 */
public class MergeTwoSortArray {

    // 使用sort工具
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        // src – 源数组。 srcPos – 源数组中的起始位置。 dest – 目标数组。 destPos – 目标数据中的起始位置。 length – 要复制的数组元素的数量。
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    // 双指针 新建一个数组 对这个数组赋值 需要开辟额外空间
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        // p1指向nums1  p2指向nums2
        int p1 = 0, p2 = 0;
        int[] sorted = new int[m + n];
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i != m + n; ++i) {
            nums1[i] = sorted[i];
        }
        System.out.println(Arrays.toString(nums1));
    }

    // 逆向双指针 不占用额外空间 倒着排 正着排会导致nums1数组元素被覆盖
    public static void merge3(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1;
        int tail = m + n - 1;
        int cur;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 == -1) {
                cur = nums2[p2--];
            } else if (p2 == -1) {
                cur = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {
                cur = nums1[p1--];
            } else {
                cur = nums2[p2--];
            }
            nums1[tail--] = cur;
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        merge3(nums1, m, nums2, n);
    }

}
