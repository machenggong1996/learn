package leetcode.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/3/14
 * @description
 */
public class FindContinuousSequence {

    /**
     * 剑指 Offer 57 - II. 和为s的连续正数序列
     *
     * @param target
     * @return
     */
    public static int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
        for (int i = 1; i <= limit; ++i) {
            for (int j = i; ; ++j) {
                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; ++k) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                    sum = 0;
                    break;
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(findContinuousSequence(9)));
    }

}
