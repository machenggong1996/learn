package leetcode.list;

/**
 * Created by machenggong on 2020/3/25.
 */
public class DeleteNode {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        if (cur.val == val) {
            return head.next;
        }
        while (cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode res = deleteNode(listNode1, 3);
    }


}
