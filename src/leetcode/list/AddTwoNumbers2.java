package src.leetcode.list;

import java.util.Stack;

/**
 * @author machenggong
 * @date 2020/04/14
 */
public class AddTwoNumbers2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();
            ListNode node = new ListNode(sum % 10);
            //反转链表
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(6);
        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);

        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(2);
        ListNode listNode7 = new ListNode(1);

        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;

        ListNode listNode = addTwoNumbers(listNode1, listNode4);

        System.out.println(listNode);

    }

}
