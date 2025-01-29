package leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/04/16
 * 合并区间
 */
public class MergingInterval {

    public static int[][] merge(int[][] arr) {
        if (arr == null || arr.length <= 1) {
            return arr;
        }
        List<int[]> list = new ArrayList<>();
        //Arrays.sort(arr,(a,b)->a[0]-b[0]);
        //按照左区间先排序
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        int i = 0;
        int n = arr.length;
        while (i < n) {
            int left = arr[i][0];
            int right = arr[i][1];
            while (i < n - 1 && right >= arr[i + 1][0]) {
                right = Math.max(right, arr[i + 1][1]);
                i++;
            }
            list.add(new int[] { left, right });
            i++;
        }
        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        int[][] arr = merge(new int[][] { { 2, 6 }, { 1, 3 }, { 8, 10 }, { 15, 18 } });
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

}
