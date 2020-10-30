package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/07/08
 */
public class DivingBoard {

    /**
     * 跳水板
     *
     * @param shorter
     * @param longer
     * @param k
     * @return
     */
    public static int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[] { shorter * k };
        }
        int[] lengths = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            lengths[i] = shorter * (k - i) + longer * i;
        }
        return lengths;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(divingBoard(1, 2, 3)));
    }

}
