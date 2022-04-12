package leetcode.offer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author machenggong
 * @since 2022/4/12
 */
public class MaxSlidingWindow {

    /**
     * 剑指 Offer 59 - I. 滑动窗口的最大值
     *
     * @param nums 数组
     * @param k    窗口大小
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[]{};
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }
        return res;
    }

    /**
     * 优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums = {1, -1};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums, 1)));
    }

}
