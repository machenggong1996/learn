import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @version 1.0
 * @author: machenggong
 * @date: 2020-03-29 9:34
 * @description: 地图分析
 */
public class MaxDistanceBFS {

    public static int maxDistance(int[][] grid) {
        int N = grid.length;

        Queue<int[]> queue = new ArrayDeque<>();
        // 将所有的陆地格子加入队列
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        // 如果我们的地图上只有陆地或者海洋，请返回 -1。
        if (queue.isEmpty() || queue.size() == N * N) {
            return -1;
        }

        int distance = -1;
        while (!queue.isEmpty()) {
            distance++;
            int n = queue.size();
            // 这里一口气取出 n 个结点，以实现层序遍历
            for (int i = 0; i < n; i++) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];
                // 遍历上方单元格
                if (r - 1 >= 0 && grid[r - 1][c] == 0) {
                    grid[r - 1][c] = 2;
                    queue.add(new int[]{r - 1, c});
                }
                // 遍历下方单元格
                if (r + 1 < N && grid[r + 1][c] == 0) {
                    grid[r + 1][c] = 2;
                    queue.add(new int[]{r + 1, c});
                }
                // 遍历左边单元格
                if (c - 1 >= 0 && grid[r][c - 1] == 0) {
                    grid[r][c - 1] = 2;
                    queue.add(new int[]{r, c - 1});
                }
                // 遍历右边单元格
                if (c + 1 < N && grid[r][c + 1] == 0) {
                    grid[r][c + 1] = 2;
                    queue.add(new int[]{r, c + 1});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        // int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int[][] grid = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        int res = maxDistance(grid);
        System.out.println(res);
    }

}
