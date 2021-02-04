package leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author machenggong
 * @date 2020/04/15
 * 每个矩阵到0的距离
 */
public class UpdateMatrix {

    int[][] directions = new int[][] { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return matrix;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] res = new int[n][m];
        // 标记当前位置是否都看过
        boolean[][] visited = new boolean[n][m];
        // BFS 队列
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 将 0 全部放入队列
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] top = queue.poll();
            int x = top[0], y = top[1];
            // BFS 搜索四个方向
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 || newX >= n || newY < 0 || newY >= m || visited[newX][newY]) {
                    continue;
                }
                res[newX][newY] = res[x][y] + 1;  // 距离更新
                visited[newX][newY] = true;
                queue.add(new int[] { newX, newY });   // 新元素入队
            }
        }
        return res;
    }

}
