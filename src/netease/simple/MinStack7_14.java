package netease.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 7-14 最小栈
 *
 * @author machenggong
 * @since 2024/2/27
 */
public class MinStack7_14 {

    static class MinStack {
        // 栈1中存放需要压栈的值
        private Stack<Integer> s1 = new Stack<Integer>();
        // 栈2中存放是最小值
        private Stack<Integer> s2 = new Stack<Integer>();

        // 压栈
        public void push(int val) {
            s1.push(val);
            // 如果栈2为空或者元素小于最小值，那么需要压入栈2
            if(s2.isEmpty() || val <= getMin()) {
                s2.push(val);
            }
        }

        // 删除栈顶元素
        public void pop() {
            int top = s1.pop();
            // 如果删除的栈顶元素刚好是最小元素，那么栈2顶部的元素需要同步删除
            if(top == getMin()) {
                s2.pop();
            }
        }

        public int top() {
            return s1.peek();
        }

        public int getMin() {
            // peek只返回、不删除栈顶元素（pop会删除栈顶元素）
            return s2.peek();
        }
    }

    /**
      push,push,push,getMin,pop,top,getMin
      -2,0,-3,,,,
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner in  = new Scanner(System.in);
        String[] opList = in.nextLine().split(",");
        String[] dataList = in.nextLine().split(",");
        List<String> resultList = new ArrayList<>();

        MinStack minStack = new MinStack();
        for(int i = 0; i < opList.length; i++) {
            String op = opList[i];
            if("push".equals(op)) {
                minStack.push(Integer.parseInt(dataList[i]));
                resultList.add("null");
            }
            if("pop".equals(op)) {
                minStack.pop();
                resultList.add("null");
            }
            if("top".equals(op)) {
                int val = minStack.top();
                resultList.add(String.valueOf(val));
            }
            if("getMin".equals(op)) {
                int val = minStack.getMin();
                resultList.add(String.valueOf(val));
            }
        }
        System.out.println(String.join(",", resultList));
    }

}
