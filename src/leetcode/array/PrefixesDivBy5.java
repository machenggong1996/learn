package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2021/1/14
 * @description
 */
public class PrefixesDivBy5 {

    /**
     * 可被 5 整除的二进制前缀
     * 方法一：模拟
     *
     * @param A
     * @return
     */
    public static List<Boolean> prefixesDivBy5(int[] A) {
        List<Boolean> list = new ArrayList<Boolean>();
        int prefix = 0;
        int length = A.length;
        for (int i = 0; i < length; i++) {
            prefix = ((prefix << 1) + A[i]) % 5;
            list.add(prefix == 0);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 1, 1, 1};
        System.out.println(prefixesDivBy5(arr));
    }


}
