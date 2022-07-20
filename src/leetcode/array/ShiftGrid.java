package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @since 2022/7/20
 */
public class ShiftGrid {

    /**
     * 1260. 二维网格迁移
     *
     * @param grid
     * @param k
     * @return
     */
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1 = (i * n + j + k) % (m * n);
                ret.get(index1 / n).set(index1 % n, grid[i][j]);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(shiftGrid(arr,1));
    }

}
