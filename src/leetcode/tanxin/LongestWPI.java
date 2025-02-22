package leetcode.tanxin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 1124 表现良好的工作时间
 * https://leetcode.cn/problems/longest-well-performing-interval/
 *
 * @author machenggong
 * @since 2025/2/13
 */
public class LongestWPI {

    /**
     * 贪心
     *
     * @param hours
     * @return
     */
    public static int longestWPI1(int[] hours) {
        int n = hours.length;
        int[] s = new int[n + 1];
        Deque<Integer> stk = new ArrayDeque<Integer>();
        stk.push(0);
        for (int i = 1; i <= n; i++) {
            s[i] = s[i - 1] + (hours[i - 1] > 8 ? 1 : -1);
            if (s[stk.peek()] > s[i]) {
                stk.push(i);
            }
        }

        int res = 0;
        for (int r = n; r >= 1; r--) {
            while (!stk.isEmpty() && s[stk.peek()] < s[r]) {
                res = Math.max(res, r - stk.pop());
            }
        }
        return res;
    }

    /**
     * 哈希表
     *
     * @param hours
     * @return
     */
    public static int longestWPI2(int[] hours) {
        int n = hours.length;
        // 哈希表 key是前缀和 value是索引
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int s = 0, res = 0;
        for (int i = 0; i < n; i++) {
            // 工作小时数大于 8 的为 1 分，小于等于 8 的为 −1 分
            s += hours[i] > 8 ? 1 : -1;
            if (s > 0) {
                res = Math.max(res, i + 1);
            } else {
                // 找前面是否有比当前前缀和小于1的 前缀和
                if (map.containsKey(s - 1)) {
                    res = Math.max(res, i - map.get(s - 1));
                }
            }
            if (!map.containsKey(s)) {
                map.put(s, i);
            }
        }
        return res;
    }

    /**
     * 单调栈
     *
     * @param hours
     * @return
     */
    public static int longestWPI3(int[] hours) {
        int n = hours.length, ans = 0;
        // 前缀和
        // 例如 nums=[1,2,−1,2]，对应的前缀和数组为 s=[0,1,3,2,4]
        // nums 的子数组 [2,−1,2] 的和就可以用 s[4]−s[1]=4−1=3 算出来
        int[] s = new int[n + 1];
        Deque<Integer> st = new ArrayDeque<>();
        // s[0]
        st.push(0);
        for (int j = 1; j <= n; ++j) {
            s[j] = s[j - 1] + (hours[j - 1] > 8 ? 1 : -1);
            if (s[j] < s[st.peek()]) {
                // 感兴趣的 j
                st.push(j);
            }
        }
        for (int i = n; i > 0; --i)
            while (!st.isEmpty() && s[i] > s[st.peek()])
                ans = Math.max(ans, i - st.pop()); // [栈顶,i) 可能是最长子数组
        return ans;
    }

    public static int findMaxLength(int[] nums) {
        int maxLength = 0;
        // key数量 value索引
        // key是前缀和 value就是i
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int counter = 0;
        map.put(counter, -1);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num > 8) {
                counter++;
            } else {
                counter--;
            }
            if (map.containsKey(counter)) {
                // 相同的前缀和说明元素个数相同
                // 如果 counter 的值在哈希表中已经存在，则取出 counter 在哈希表中对应的下标 prevIndex
                // nums 从下标 prevIndex+1 到下标 i 的子数组中有相同数量的 0 和 1，该子数组的长度为 i−prevIndex，
                // 使用该子数组的长度更新最长连续子数组的长度；
                int prevIndex = map.get(counter);
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                // 如果 counter 的值在哈希表中不存在，则将当前余数和当前下标 i 的键值对存入哈希表中
                map.put(counter, i);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        int[] arr = {9, 9, 6, 0, 6, 6, 9};
        System.out.println(longestWPI1(arr));
        System.out.println(longestWPI2(arr));
        System.out.println(longestWPI3(arr));
        System.out.println(findMaxLength(arr));
    }

}
