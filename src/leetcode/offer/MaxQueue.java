package leetcode.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author machenggong
 * @date 2021/3/14
 * @description
 */
public class MaxQueue {

    Queue<Integer> q;
    Deque<Integer> d;

    public MaxQueue() {
        q = new LinkedList<Integer>();
        d = new LinkedList<Integer>();
    }

    public int max_value() {
        if (d.isEmpty()) {
            return -1;
        }
        return d.peekFirst();
    }

    public void push_back(int value) {
        while (!d.isEmpty() && d.peekLast() < value) {
            d.pollLast();
        }
        d.offerLast(value);
        q.offer(value);
    }

    public int pop_front() {
        if (q.isEmpty()) {
            return -1;
        }
        int ans = q.poll();
        if (ans == d.peekFirst()) {
            d.pollFirst();
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();

    }

}
