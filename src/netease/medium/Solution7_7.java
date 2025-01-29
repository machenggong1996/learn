package netease.medium;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 7-7 连通网络的操作次数
 *
 * @author machenggong
 * @since 2024/2/29
 */
public class Solution7_7 {

    class UnionFind {
        // 在并查集数据结构中，int[] parent;是一个整数数组，用来表示每个节点（或者元素）的父节点。
        // 在并查集中，每个节点最初被视为一个独立的集合，因此初始时，每个节点的父节点都是它自己。
        // 随着合并操作的进行，节点的父节点会发生变化，最终形成一个树状结构，树根即代表整个集合。
        int[] parent;
        // 在并查集数据结构中，int[] rank 用于存储树的高度（或者称为秩）。
        // 这个秩的概念是在并查集的优化中引入的，它被用来在合并操作时优化树的高度，以确保树的高度尽可能小，从而提高并查集操作的效率。
        // 通过维护秩，我们可以使得合并后的树尽可能平衡，而不是简单地将一个树连接到另一个树上。
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            // n台计算机 i代表每台计算机
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public int makeConnected(int n, int[][] connections) {
        // 如果线的数量小于n-1，那么肯定不行
        if (connections.length < n - 1) return -1;

        UnionFind uf = new UnionFind(n);
        for (int[] connection : connections) {
            uf.union(connection[0], connection[1]);
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(uf.find(i));
        }

        return set.size() - 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nm = scanner.nextLine();
        String[] nmArr = nm.split(" ");
        // n个计算机
        int n = Integer.parseInt(nmArr[0]);
        // m条线
        int m = Integer.parseInt(nmArr[1]);
        int[][] connections = new int[m][2];
        for (int i = 0; i < m; i++) {
            String input = scanner.nextLine();
            String[] numbers = input.split(" ");
            for (int j = 0; j < numbers.length; j++) {
                connections[i][j] = Integer.parseInt(numbers[j]);
            }
        }
        Solution7_7 solution = new Solution7_7();
        // 4 3
        // 0 1
        // 0 2
        // 1 2
        System.out.println(solution.makeConnected(n, connections));
    }

}
