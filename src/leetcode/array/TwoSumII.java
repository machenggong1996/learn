package leetcode.array;

/**
 * @author machenggong
 * @date 2020/07/20
 */
public class TwoSumII {

    /**
     * 二分查找
     * 两数之和 II - 输入有序数组
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum1(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[] { i + 1, mid + 1 };
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[] { -1, -1 };
    }

    /**
     * 双指针
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[] { low + 1, high + 1 };
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        twoSum1(new int[] { 2, 3, 7, 11, 15 }, 9);
        twoSum2(new int[] { 2, 7, 11, 15 }, 9);
    }

}
