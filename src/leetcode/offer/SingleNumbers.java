package leetcode.offer;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/3/14
 * @description
 */
public class SingleNumbers {

    /**
     * 剑指 Offer 56 - I. 数组中数字出现的次数
     *
     * @param nums
     * @return
     */
    public static int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        // 找从右向左数第几位不同，也就是第index位
        // 这一步其实可以根据n & (-n)的快捷方式获得，不过对位运算掌握不是那么熟练的话，记结论容易忘，不如理解实质
        int div = 1;
        while ((div & ret) == 0) {
            div <<= 1;
        }
        //分组
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    public static int[] singleNumbers1(int[] nums) {
        int sum = 0;
        //得到异或结果，即为不相同两个数的异或结果sum
        for (int num : nums) {
            sum ^= num;
        }
        System.out.println(Integer.toBinaryString(sum));
        System.out.println(Integer.toBinaryString(-sum));
        //得到sum的二进制的1的最低位 可以获取两个结果从哪位开始不同
        int flag = (-sum) & sum;
        int[] result = new int[2];
        //分成两个组进行异或，每组异或后的结果就是不相同两个数的其中之一
        for (int num : nums) {
            if ((flag & num) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }

    /**
     * 面试题56 - II. 数组中数字出现的次数 II
     *
     * @param nums
     * @return
     */
    public static int singleNumberII(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static int singleNumberII2(int[] nums) {
        int res = 0, bit = 0;
        for (int i = 30; i >= 0; i--) {
            for (int num : nums) {
                //求出所有二进制位的和
                bit += (num >> i) & 1;
            }
            res = res << 1;
            res += bit % 3;
            bit = 0;
        }
        return res;
    }

    /**
     * 剑指 Offer 56 - II. 数组中数字出现的次数 II
     * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字
     * 有限状态机
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 10, 4, 1, 4, 3, 3};
        int[] nums1 = {4, 1, 4, 3};
        System.out.println(Arrays.toString(singleNumbers1(nums1)));
        int[] nums2 = {4, 3, 3, 3};
        System.out.println(singleNumberII(nums2));
        System.out.println(singleNumberII2(nums2));
    }

}
