package leetcode.offer;

/**
 * Created by machenggong on 2020/3/13.
 */
public class FindNumberIn2DArray {

    /**
     * 二维数组中的查找
     * 从右上角开始查找
     *
     * @param matrix
     * @param target
     * @return
     */

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[][] = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

        System.out.println(findNumberIn2DArray(arr, 5));
    }

}
