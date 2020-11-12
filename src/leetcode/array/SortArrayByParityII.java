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

    public static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParityII(new int[] { 4, 2, 5, 7, 9, 8 })));
    }

}