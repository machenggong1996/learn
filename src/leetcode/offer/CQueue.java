package leetcode.offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author machenggong
 * @date 2021/3/4
 * @description 用两个栈实现队列
 */
public class CQueue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public CQueue() {
        // 第一个栈支持插入操作，第二个栈支持删除操作
        stack1 = new LinkedList<Integer>();
        stack2 = new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        // 如果第二个栈为空
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        // 放完之后还是空
        if (stack2.isEmpty()) {
            return -1;
        } else {
            int deleteItem = stack2.pop();
            return deleteItem;
        }
    }

    public static void main(String[] args) {
        CQueue queue = new CQueue();
        queue.appendTail(1);
        queue.appendTail(8);
        queue.deleteHead();
        queue.appendTail(7);
        queue.deleteHead();

    }

}
