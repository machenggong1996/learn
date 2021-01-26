package leetcode.array;

/**
 * @author machenggong
 * @date 2021/1/26
 * @description
 */
public class NumEquivDominoPairs {

    /**
     * 等价多米诺骨牌对的数量
     * 二元组表示 + 计数
     *
     * @param dominoes
     * @return
     */
    public static int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] dominoes = new int[][]{{1, 2}, {2, 1}, {3, 4},{3,4}, {5, 6}};
        System.out.println(numEquivDominoPairs(dominoes));
    }

}
