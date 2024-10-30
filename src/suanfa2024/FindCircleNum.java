package suanfa2024;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 547. 省份数量
 * https://leetcode.cn/problems/number-of-provinces/description/
 *
 * @author machenggong
 * @since 2024/10/21
 */
public class FindCircleNum {

    // 深度优先搜索
    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        // 打标记 找过的城市不再找了
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, cities, i);
                provinces++;
            }
        }
        return provinces;
    }

    // i代表城市
    public void dfs(int[][] isConnected, boolean[] visited, int cities, int i) {
        //j代表和哪个城市有关联
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                // 去寻找和j有关联的城市
                dfs(isConnected, visited, cities, j);
            }
        }
    }

    // 广度优先搜索
    public int findCircleNum1(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < cities; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                provinces++;
            }
        }
        return provinces;
    }

    // 并查集
    public int findCircleNum2(int[][] isConnected) {
        int cities = isConnected.length;
        int[] parent = new int[cities];
        for (int i = 0; i < cities; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < cities; i++) {
            for (int j = i + 1; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (parent[i] == i) {
                provinces++;
            }
        }
        return provinces;
    }

    public void union(int[] parent, int index1, int index2) {
        // 将index2的父节点设置为index1的父节点
        parent[find(parent, index1)] = find(parent, index2);
    }

    // 寻找父节点
    public int find(int[] parent, int index) {
        // 如果找不到父节点，则说明是根节点
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }


    public static void main(String[] args) {
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        FindCircleNum findCircleNum = new FindCircleNum();
//        System.out.println(findCircleNum.findCircleNum(arr));
//        System.out.println(findCircleNum.findCircleNum1(arr));
        System.out.println(findCircleNum.findCircleNum2(arr));
    }

}
