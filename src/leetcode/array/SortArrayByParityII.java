package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/11/12
 */
public class SortArrayByParityII {

    /**
     * 按奇偶排序数组 II
     *
     * @param A
     * @return
     */
    public static int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        //i为偶数指针 j为奇数指针
        int j = 1;
        for (int i = 0; i < n; i += 2) {
            if (A[i] % 2 == 1) {
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    /**
     * 不改变相对顺序
     *
     * @param array
     */
    public static void reOrderArray(int[] array) {
        /**
         * 算法思路：
         * 采用冒泡法思想，只要遇到前偶后奇则交换
         */
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 不改变相对顺序
     *
     * @param array
     * @return
     */
    public static int[] reOrder(int[] array) {
        int[] resArr = new int[array.length];
        int index = 0;
        for (int k : array) {
            if (k % 2 == 1) {
                resArr[index] = k;
                index++;
            }
        }

        for (int j : array) {
            if (j % 2 == 0) {
                resArr[index] = j;
                index++;
            }
        }

        return resArr;
    }

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParityII(new int[]{4, 2, 5, 7, 9, 8})));
        int[] arr = new int[]{4, 2, 5, 7, 9, 8};
        reOrderArray(arr);
        System.out.println("不改变相对顺序");
        System.out.println(Arrays.toString(arr));
        int[] arr1 = new int[]{4, 2, 5, 7, 9, 8};
        System.out.println(Arrays.toString(reOrder(arr1)));
    }

}
