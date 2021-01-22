package leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/22
 * @description
 */
public class AddToArrayForm {

    /**
     * 989. 数组形式的整数加法
     *
     * @param A
     * @param K
     * @return
     */
    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<Integer>();
        int n = A.length;
        for (int i = n - 1; i >= 0; --i) {
            int sum = A[i] + K % 10;
            K /= 10;
            if (sum >= 10) {
                K++;
                sum -= 10;
            }
            res.add(sum);
        }
        for (; K > 0; K /= 10) {
            res.add(K % 10);
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] A = new int[]{9, 9, 9, 9};
        System.out.println(addToArrayForm(A, 34));
    }

}
