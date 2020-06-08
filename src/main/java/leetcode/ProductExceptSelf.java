package leetcode;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/06/04
 * 除自身以外数组的乘积
 */
public class ProductExceptSelf {

    /**
     * 不必将所有数字的乘积除以给定索引处的数字得到相应的答案，
     * 而是利用索引左侧所有数字的乘积和右侧所有数字的乘积（即前缀与后缀）相乘得到答案。
     *
     * @param nums
     * @return
     */
    public static int[] productExceptSelf(int[] nums) {
        //左数组
        int[] res1 = new int[nums.length];
        //右数组
        int[] res2 = new int[nums.length];
        int[] res = new int[nums.length];
        //初始为1
        res2[nums.length - 1] = 1;
        res1[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            res1[i] = res1[i - 1] * nums[i - 1];
        }
        for (int j = nums.length - 2; j >= 0; j--) {
            res2[j] = res2[j + 1] * nums[j + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            res[i] = res1[i] * res2[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[] { 1, 2, 3, 4 })));
    }

}
