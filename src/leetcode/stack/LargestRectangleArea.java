package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 84 柱状图中最大的矩形
 *
 * @author machenggong
 * @since 2024/12/17
 */
public class LargestRectangleArea {

    /**
     * 暴力法
     * O(n^2)
     * 会超出时间限制
     * @param heights
     * @return
     */
    public static int largestRectangleArea1(int[] heights) {
        int n = heights.length;
        int ans = 0;
        // 枚举左边界
        for (int left = 0; left < n; ++left) {
            int minHeight = Integer.MAX_VALUE;
            // 枚举右边界
            for (int right = left; right < n; ++right) {
                // 确定高度
                minHeight = Math.min(minHeight, heights[right]);
                // 计算面积
                ans = Math.max(ans, (right - left + 1) * minHeight);
            }
        }
        return ans;
    }

    /**
     * 单调栈
     * @param heights
     * @return
     */
    public static int largestRectangleArea2(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        mono_stack.clear();
        for (int i = n - 1; i >= 0; --i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                mono_stack.pop();
            }
            right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }

    /**
     * 单调栈 + 常数优化
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // 左边界
        int[] left = new int[n];
        // 右边界
        int[] right = new int[n];
        Arrays.fill(right, n);

        // 栈中存放的是索引
        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            // 看到的元素高度小于栈顶元素高度 则栈顶元素出栈
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                // 发现栈不再是继续增加就把栈顶元素出栈 右边界变成统一的i
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 6, 2, 3};
        int[] nums1 = {6, 7, 5, 2, 4, 5, 9, 3};
        System.out.println(largestRectangleArea(nums1));
    }

}
