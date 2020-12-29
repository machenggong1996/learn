package leetcode.tanxin;

/**
 * @author machenggong
 * @date 2020/12/29
 * @description
 */
public class MinPatches {

    /**
     * 330. 按要求补齐数组
     * 对于正整数 x，如果区间 [1,x-1] 内的所有数字都已经被覆盖，且 x 在数组中，则区间 [1,2x-1] 内的所有数字也都被覆盖。证明如下。
     * <p>
     * 对于任意 1≤y<x，y 已经被覆盖，x 在数组中，因此 y+x 也被覆盖，区间 [x+1,2x-1]（即区间 [1,x-1] 内的每个数字加上 x之后得到的区间）内的所有数字也被覆盖，由此可得区间 [1,2x-1] 内的所有数字都被覆盖。
     * <p/>
     * <a href = "https://leetcode-cn.com/problems/patching-array/solution/an-yao-qiu-bu-qi-shu-zu-by-leetcode-solu-klp1/">题解</a>
     * @param nums 数组
     * @param n    范围
     * @return 至少添加几个数可以完全覆盖数组的所有数
     */
    public static int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }

    public static void main(String[] args) {
        System.out.println(minPatches(new int[]{1, 3}, 6));
    }

}
