package leetcode.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author machenggong
 * @date 2021/1/15
 * @description
 */
public class RemoveStones {

    public int removeStones(int[][] stones) {
        UnionFind unionFind = new UnionFind();

        for (int[] stone : stones) {
            // 下面这三种写法任选其一
            // unionFind.union(~stone[0], stone[1]);
            // unionFind.union(stone[0] - 10000, stone[1]);
            unionFind.union(stone[0] + 10000, stone[1]);
        }
        return stones.length - unionFind.getCount();
    }

    public static void main(String[] args) {
        RemoveStones removeStones = new RemoveStones();
        int[][] stones = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
        System.out.println(removeStones.removeStones(stones));
    }

    private class UnionFind {

        private Map<Integer, Integer> parent;
        private int count;

        public UnionFind() {
            this.parent = new HashMap<>();
            this.count = 0;
        }

        public int getCount() {
            return count;
        }

        public int find(int x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                count++;
            }

            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent.put(rootX, rootY);
            count--;
        }
    }

}
