package leetcode;

/**
 * @author machenggong
 * @date 2020/06/17
 */
public class MaxScoreSightseeingPair {
    /**
     * 1014. 最佳观光组合
     */
    public static int maxScoreSightseeingPair(int[] A) {

        int left = A[0], res = Integer.MIN_VALUE;
        for (int j = 1; j < A.length; j++) {

            res = Math.max(res, left + A[j] - j);
            //A[j] + j 这个数字是固定的 对于某一位置来说 每次做差的时候都要带上
            //A[i]+i+A[j]−j 下面的A[j] + j 相当于 通过一次遍历动态维护
            left = Math.max(left, A[j] + j);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(maxScoreSightseeingPair(new int[] { 1, 10, 5, 2, 6 }));
    }

}
