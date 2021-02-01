package leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author machenggong
 * @date 2021/2/1
 * @description
 */
public class FairCandySwap {

    /**
     * 888 公平的糖果交换
     * sumA−x+y=sumB+x−y
     * 爱丽丝的大小为 xx 的糖果棒与鲍勃的大小为 yy 的糖果棒交换
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = Arrays.stream(A).sum();
        int sumB = Arrays.stream(B).sum();
        int delta = (sumA - sumB) / 2;
        Set<Integer> rec = new HashSet<Integer>();
        for (int num : A) {
            rec.add(num);
        }
        int[] ans = new int[2];
        for (int y : B) {
            int x = y + delta;
            if (rec.contains(x)) {
                ans[0] = x;
                ans[1] = y;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 5};
        int[] B = new int[]{2, 4};
        System.out.println(Arrays.toString(fairCandySwap(A, B)));
    }

}
