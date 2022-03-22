package leetcode.offer;

import java.util.Stack;

/**
 * @author machenggong
 * @since 2022/3/22
 */
public class ValidateStackSequences {

    /**
     * 剑指 Offer 31. 栈的压入、弹出序列
     *
     * @param pushed 入栈顺序
     * @param popped 弹出顺序
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); // num 入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(pushed, popped));
    }

}
