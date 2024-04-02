package netease.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/2/22
 */
public class MountainCount7_3 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String[] MN = sc.nextLine().split(" ");
        int M = Integer.parseInt(MN[0]);
        int N = Integer.parseInt(MN[1]);

        String[][] mountainMatrix = new String[M][N];
        for (int i = 0; i < M; i++) {
            mountainMatrix[i] = sc.nextLine().split(" ");
        }
        int[][] nums = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                nums[i][j] = Integer.parseInt(mountainMatrix[i][j]);
            }
        }
        if (nums.length == 0)
            System.out.println(0);
        else {
            int count = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (nums[i][j] == 1) {
                        count++;
                        dfs(nums, i, j);
                    }

                }
            }
            System.out.println(count);
        }
    }

    public static void dfs(int[][] nums, int i, int j) {
        int a = nums.length;
        int b = nums[0].length;
        if (i < 0 || j < 0 || i >= a || j >= b || nums[i][j] == 0) {
            return;
        }
        nums[i][j] = 0;
        dfs(nums, i - 1, j);
        dfs(nums, i + 1, j);
        dfs(nums, i, j - 1);
        dfs(nums, i, j + 1);
    }

}
