package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @since 2021/4/12
 */
public class LargestNumber {

    /**
     * 179. 最大数
     *
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        int n = nums.length;
        // 转换成包装类型，以便传入 Comparator 对象（此处为 lambda 表达式）
        Integer[] numsArr = new Integer[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numsArr[0] == 0) {
            return "0";
        }
        StringBuilder ret = new StringBuilder();
        for (int num : numsArr) {
            ret.append(num);
        }
        return ret.toString();
    }

    public static int sortTest(int x, int y) {
        long sx = 10, sy = 10;
        while (sx <= x) {
            sx *= 10;
        }
        while (sy <= y) {
            sy *= 10;
        }
        return (int) (-sy * x - y + sx * y + x);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 2};
        System.out.println(largestNumber(nums));
        System.out.println(sortTest(2, 10));
    }

}
