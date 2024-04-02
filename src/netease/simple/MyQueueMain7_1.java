package netease.simple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author machenggong
 * @since 2024/2/20
 */
public class MyQueueMain7_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] operationList = in.nextLine().split(",");
        String[] dataList = in.nextLine().split(",", -1);
        List<String> result = new ArrayList<>(operationList.length);
        MyQueue queue = new MyQueue();
        for (int i = 0; i < operationList.length; i++) {
            switch (operationList[i]) {
                case "push":
                    queue.push(Integer.parseInt(dataList[i]));
                    result.add("null");
                    break;
                case "peek":
                    result.add(String.valueOf(queue.peek()));
                    break;
                case "pop":
                    result.add(String.valueOf(queue.pop()));
                    break;
                case "empty":
                    result.add(String.valueOf(queue.empty()));
                    break;
            }
        }
        StringBuilder resultStr = new StringBuilder();
        for (String s : result) {
            resultStr.append(s).append(",");
        }
        resultStr.deleteCharAt(resultStr.length() - 1);
        System.out.println(resultStr);

    }

    public static class MyQueue {

        Stack<Integer> input;
        Stack<Integer> output;

        public MyQueue() {
            input = new Stack<>();
            output = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            input.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (!output.isEmpty()) {
                return output.pop();
            } else {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
                return output.pop();
            }

        }

        /**
         * Get the front element.
         */
        public int peek() {

            if (!output.isEmpty()) {
                return output.peek();
            } else {
                while (!input.isEmpty()) {
                    output.push(input.pop());
                }
                return output.peek();

            }


        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return output.isEmpty() && input.isEmpty();
        }

    }
}

