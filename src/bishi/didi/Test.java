package bishi.didi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class Test {


    /**
     * 1,3
     * 2,6
     * 8,10
     * 15,18
     * <p>
     * <p>
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
     * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *      示例 1：  输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     *  输出：[[1,6],[8,10],[15,18]] 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6]. 示例 2：  输入：intervals = [[1,4],[4,5]] 输出：[[1,5]] 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */

    public static List<int[]> merge(int[][] arr) {
        // 1. 排序
        sortArr(arr);
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int[] as = arr[i];
            while (i<as.length-1&&as[1] >= arr[i + 1][0]) {
                as = new int[]{as[0], arr[i + 1][1]};
                i++;
            }
            res.add(as);
        }
        return res;
    }

    private static void sortArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j][0] > arr[j + 1][0]) {
                    int[] temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {8, 10}, {2, 6} ,{15, 18}};
        int[][] arr1 = {{1,4},{4,5}};
        List<int[]> res = merge(arr1);
        for (int[] a : res) {
            System.out.println(Arrays.toString(a));
        }

    }


}
