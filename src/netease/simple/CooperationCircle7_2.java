package netease.simple;

import java.util.Scanner;

/**
 * 合作圈 并查集
 * https://blog.csdn.net/OddSmurfs/article/details/93299953
 *
 * @author machenggong
 * @since 2024/2/21
 */
public class CooperationCircle7_2 {

//    public static void main(String[] args) {
//        // 输入 1 1 0|1 1 0|0 0 1
////        Scanner in = new Scanner(System.in);
////        String[] operationList = in.nextLine().split("\\|");
////        int[][] M = new int[operationList.length][operationList.length];
////        int i = 0;
////        for (String s : operationList){
////            String[] s1 = s.split(" ");
////            int j = 0;
////            for (String s2 : s1){
////                M[i][j] = Integer.parseInt(s2);
////                j++;
////            }
////            i++;
////        }
//        //int[][] M = {{1,1,0},{1,1,0},{0,0,1}};
//        int[][] M = {{1,1,1,0},{1,1,1,0},{1,1,1,0},{0,0,0,1}};
//        System.out.println(findCircleNum(M));
//    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String[] operationList = in.nextLine().split("\\|");
//        int[][] M = new int[operationList.length][operationList.length];
//        int i = 0;
//        for (String s : operationList) {
//            String[] s1 = s.split(" ");
//            int j = 0;
//            for (String s2 : s1) {
//                M[i][j] = Integer.parseInt(s2);
//                j++;
//            }
//            i++;
//        }
        int[][] M = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int circles = findCircleNum1(M);
        System.out.println(circles);
    }

    public static int findCircleNum1(int[][] M) {
        int n = M.length;
        int count = 0;
        // 用于记录员工是否被访问过
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private static void dfs(int[][] M, boolean[] visited, int person) {
        visited[person] = true;
        for (int i = 0; i < M.length; i++) {
            if (M[person][i] == 1 && !visited[i]) {
                dfs(M, visited, i);
            }
        }
    }

    public static int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1 && !ufs.isInSameSet(i, j)) {
                    ufs.union(i, j);
                }
            }
        }
        return ufs.getSize();
    }

    public static class UnionFindSet {
        int[] arr;
        int size;

        public UnionFindSet(int size) {
            this.arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = -1;
            }
            this.size = size;
        }

        //寻找父节点，没有就返回自己
        public int find(int x) {
            while (arr[x] >= 0) {
                x = arr[x];
            }
            return x;
        }

        public boolean isInSameSet(int a, int b) {
            return find(a) == find(b);
        }

        //合并的时候先找根节点
        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            arr[rootA] += arr[rootB];
            arr[rootB] = rootA;
        }

        public int getSize() {
            int count = 0;
            for (int i : arr) {
                if (i < 0) {
                    count++;
                }
            }
            return count;
        }
    }

}
