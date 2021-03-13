package leetcode.offer;

import java.util.Stack;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class GetMinStack {

    /**
     * 数据栈 AA ： 栈 AA 用于存储所有元素，保证入栈 push() 函数、出栈 pop() 函数、获取栈顶 top() 函数的正常逻辑。
     * 辅助栈 BB ： 栈 BB 中存储栈 AA 中所有 非严格降序 的元素，则栈 AA 中的最小元素始终对应栈 BB 的栈顶元素，即 min() 函数只需返回栈 BB 的栈顶元素即可
     */
    Stack<Integer> A, B;

    public GetMinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }

    public void push(int x) {
        A.add(x);
        if (B.empty() || B.peek() >= x) {
            B.add(x);
        }
    }

    public void pop() {
        if (A.pop().equals(B.peek())) {
            B.pop();
        }
    }

    public int top() {
        return A.peek();
    }

    public int min() {
        return B.peek();
    }

    public static void main(String[] args) {
        GetMinStack minStack = new GetMinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int min1 = minStack.min();   // --> 返回 -3.
        minStack.pop();
        minStack.top();   // --> 返回 0.
        int min2 = minStack.min();   //--> 返回 -2.
    }

}
