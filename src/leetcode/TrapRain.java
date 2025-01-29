package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by machenggong on 2020/3/11.
 */
public class TrapRain {

    /**
     * 接雨水
     * 按每一个柱子计算： 每一个柱子的高度方向可以接的雨水的数量 = min(从当前柱子向左看的最高柱子高度,
     * 从当前柱子向右看的最高柱子高度) - 当前柱子高度
     * <p>
     * 步骤：
     * 1）两个数组left、right分别保存：从左往右遍历时下标i最高柱子高度，和从右往左遍历时下标i最高柱子高度。
     * 2）再遍历一遍每个位置，只有当height[i]的高度，比left[i]和right[i]都要小的时候才能接住雨水（否则总有一边会漏，接不到雨水）
     * 3）将所有可以接到雨水的柱子的数量加起来
     *
     * @param height
     * @return
     */

    public static int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 单调栈
     * @param height
     * @return
     */
    public static int trap1(int[] height) {
        int ans = 0;
        // 存储柱子索引下标
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                // 栈为空，没有左边界，无法接雨水
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                // 计算当前柱子和栈顶柱子之间的水的高度
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr));
        System.out.println(trap1(arr));
    }

}
