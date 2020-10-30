package leetcode.array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author machenggong
 * @date 2020/06/11
 */
public class DailyTemperatures {

    /**
     * 每日温度
     * 单调栈 栈里面存下标 使用下标进行做差 栈中的温度由大到小
     * @param T
     * @return
     */
    public static int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < length; i++) {
            int temperature = T[i];
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static int[] dailyTemperatures1(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                //当新的元素进栈时会将:从栈顶开始将所有比它小的元素挤出去,注意是所有
                stack.pop();
            }
            if (stack.isEmpty()) {
                res[i] = 0;
            } else {
                //计算出新入栈的元素和第一个比它大的元素的索引差值,再放入数组
                res[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
    }

}
