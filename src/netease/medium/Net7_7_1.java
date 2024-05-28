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
public class Net7_7_1 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String[] fristLines = in.nextLine().split(" ");
        int n = Integer.parseInt(fristLines[0]);
        int m = Integer.parseInt(fristLines[1]);
        if(n - 1 > m){
            System.out.println(-1);
            return;
        }
        int[][] nums = new int[m][2];
        for(int i = 0;i<m;i++){
            String[] str = in.nextLine().split(" ");
            nums[i][0] = Integer.parseInt(str[0]);
            nums[i][1] = Integer.parseInt(str[1]);
        }
        System.out.println(handle(nums,n,m));
    }

    public static int handle(int[][] nums,int n,int m){
        UnionFind uf = new UnionFind(n);
        Set<Integer> set = new HashSet<>();
        for (int[] num : nums) {
            uf.union(num[0], num[1]);
        }
        for(int i = 0;i<n;i++){
            set.add(uf.find(i));
        }

        return set.size() - 1 ;
    }

    public static class UnionFind{
         int[] parent;
         int[] rank;
        public UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i = 0;i < n;i++){
                parent[i] = i;
            }
        }

        public int find(int x){
            if(x != parent[x]){
                return find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY){
                return;
            }
            if(rank[rootX] == rank[rootY]){
                rank[rootX]++;
                parent[rootY] = rootX;
            }else if(rank[rootX] > rank[rootY]){
                parent[rootY] = rootX;
            }else{
                parent[rootX] = rootY;
            }
        }

    }

}
