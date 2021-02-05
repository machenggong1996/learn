package leetcode;

/**
 * Created by machenggong on 2020/3/11.
 */
public class MergeTwoLists {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode resNode = new ListNode(0);
        ListNode node = resNode;

        while (l1 != null || l2 != null) {

            if (l1 != null && l2 != null && l1.val < l2.val) {
                node.next = new ListNode(l1.val);
                l1 = l1.next;
            } else if (l1 != null && l2 == null) {
                node.next = l1;
                l1 = null;
            } else if (l1 == null && l2 != null) {
                node.next = l2;
                l2 = null;
            } else {
                node.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            node = node.next;
        }

        return resNode.next;
    }

    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);

        listNode1.next = listNode2;
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);
        ListNode listNode7 = new ListNode(5);
        ListNode listNode8 = new ListNode(8);
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;

       ListNode node = mergeTwoLists1(listNode1, listNode4);
       System.out.println();
    }


}
