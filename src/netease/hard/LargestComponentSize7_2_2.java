package netease.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author machenggong
 * @since 2024/4/1
 */
public class LargestComponentSize7_2_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(" ");
        int[] nums = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }
        Arrays.sort(nums);
        int m = nums[nums.length - 1];

        UnionFind uf = new UnionFind(m+1);
        //因为在算公因数。
        //a x b = c
        //那我遍历i：i * i 如果大于c了，就不用遍历了。比如：2x3=6。 3x3=9。如果他们有其他因数，肯定是一个小于这个数字一个大于这个数字的。
        //
        //下面就是，如果他是因数，我就把两个都加进去。比如2x3=6。我直接把2和3都加进去。
        //
        //这两个操作要么都没有，就是纯暴力。
        //要么必须都有就是这个代码。
        //如果只有一个，就是不对的
        // a x b = c
        //必定有：a小于等于c的开方，且b大于等于c的开方。
        for (int n : nums) {
            for (int i = 2; i * i <= n; i++) {
                // 余数为零，说明是因数
                if (n % i == 0) {
                    uf.union(n, i);
                    uf.union(n, n / i);
                }
            }
        }
        int[] count = new int[m+1];
        int ans = 0;
        for (int n : nums) {
            int root = uf.find(n);
            count[root]++;
            ans = Math.max(ans, count[root]);
        }
        System.out.println(ans);
    }

    public static class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }
        }
    }

}
