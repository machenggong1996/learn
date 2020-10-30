package leetcode.array;

/**
 * @author machenggong
 * @date 2020/04/20
 */
public class NumIslands {

    /**
     * 岛屿个数
     * @param grid
     * @return
     */
    public static int numIslands(char[][] grid) {
        int islandNum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }

    //感染函数
    public static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }

    public static void main(String[] args) {
        /**
         * 11110
         * 11010
         * 11000
         * 00000
         */
        char[][] land = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
                        { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
        System.out.println(numIslands(land));
    }

}
