package suanfa2024;

/**
 * 反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
 * https://blog.csdn.net/rainchxy/article/details/142218486
 * @author machenggong
 * @since 2024/10/28
 */
public class ReverseList {


    // 迭代
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // 递归 两个节点反转 扩展到多节点反转 将小问题解决 直到所有问题解决
    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        ListNode node = reverseList(head);

        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
//        ListNode nodeX1 = reverseList1(head);
//        while (nodeX1 != null) {
//            System.out.println(nodeX1.val);
//            nodeX1 = nodeX1.next;
//        }
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
