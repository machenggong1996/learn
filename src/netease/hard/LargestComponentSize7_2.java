package netease.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/3/8
 */
class UnionFind {
    private int[] parent;
    private int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
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
        if (rootX != rootY) {
            if (size[rootX] < size[rootY]) {
                int temp = rootX;
                rootX = rootY;
                rootY = temp;
            }
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }
}

public class LargestComponentSize7_2 {
    public int largestComponentSize(int[] nums) {
        int maxNum = 0;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
        }

        UnionFind uf = new UnionFind(maxNum + 1);

        for (int num : nums) {
            for (int j = 2; j <= Math.sqrt(num); j++) {
                if (num % j == 0) {
                    uf.union(num, j);
                    uf.union(num, num / j);
                }
            }
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            int root = uf.find(num);
            count.put(root, count.getOrDefault(root, 0) + 1);
        }

        int result = 0;
        for (int value : count.values()) {
            result = Math.max(result, value);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] split = input.split(" ");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        LargestComponentSize7_2 solution = new LargestComponentSize7_2();
        System.out.println(solution.largestComponentSize(nums)); // 输出结果为 4
    }
}
