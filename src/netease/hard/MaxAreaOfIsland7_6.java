package netease.hard;

import java.util.Scanner;

/**
 * 7-6 岛屿最大面积
 *
 * @author machenggong
 * @since 2024/3/6
 */
public class MaxAreaOfIsland7_6 {

    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        int area = 1;
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);
        return area;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String arrStr = scanner.nextLine().replace("[","").replace("]","");
        String[] split = arrStr.split(";");
        int m = split.length;
        int n = split[0].split(",").length;
        int[][] grid = new int[m][n];
        for (int i = 0 ; i< split.length ; i++){
            String arrStrN = split[i];
            String[] arrn = arrStrN.split(",");
            for (int j = 0; j < arrn.length; j++){
                grid[i][j] = Integer.parseInt(arrn[j]);
            }
        }
        System.out.println(maxAreaOfIsland(grid));
    }
}
