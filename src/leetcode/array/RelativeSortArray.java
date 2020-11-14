package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/11/14
 */
public class RelativeSortArray {

    /**
     * 1122. 数组的相对排序
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {

        int[] bucket = new int[1001];
        for (int i = 0; i < arr1.length; i++) {
            bucket[arr1[i]]++;
        }

        int idx = 0;
        for (int i = 0; i < arr2.length; i++) {
            while (bucket[arr2[i]] > 0) {
                bucket[arr2[i]]--;
                arr1[idx++] = arr2[i];
            }
        }

        for (int i = 0; i < bucket.length; i++) {
            if (idx == arr1.length - 1) {
                break;
            }
            while (bucket[i] > 0) {
                bucket[i]--;
                arr1[idx++] = i;
            }
        }

        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
        int[] arr2 = new int[] { 2, 1, 4, 3, 9, 6 };
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }

}
