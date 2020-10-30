package leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/07/14
 */
public class MinimumTotal {

    /**
     * 120. 三角形最小路径和
     * 汉诺塔问题 动态规划
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> in1 = new ArrayList<>();
        in1.add(2);
        List<Integer> in2 = new ArrayList<>();
        in2.add(3);
        in2.add(4);
        List<Integer> in3 = new ArrayList<>();
        in3.add(6);
        in3.add(5);
        in3.add(7);
        List<Integer> in4 = new ArrayList<>();
        in4.add(4);
        in4.add(1);
        in4.add(8);
        in4.add(3);
        triangle.add(in1);
        triangle.add(in2);
        triangle.add(in3);
        triangle.add(in4);
        System.out.println(minimumTotal(triangle));
    }

}
