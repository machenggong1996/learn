package leetcode.array;

/**
 * @author machenggong
 * @since 2021/7/15
 */
public class MaximumElementAfterDecrementingAndRearranging {

    /**
     * 减小和重新排列数组后的最大元素
     *
     * @param arr
     * @return
     */
    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] cnt = new int[n + 1];
        for (int v : arr) {
            ++cnt[Math.min(v, n)];
        }
        // 不存在的元素个数
        int miss = 0;
        for (int i = 1; i <= n; ++i) {
            // 说明缺失元素 i，我们需要在后续找一个大于 i 的元素，将其变更为 i
            if (cnt[i] == 0) {
                ++miss;
            } else {
                miss -= Math.min(cnt[i] - 1, miss); // miss 不会小于 0，故至多减去 miss 个元素
            }
        }
        return n - miss;
    }

    public static void main(String[] args) {
        int[] arr = {100,1,1000};
        System.out.println(maximumElementAfterDecrementingAndRearranging(arr));
    }

}
