package leetcode.list;

/**
 * @author machenggong
 * @date 2020/11/20
 */
public class InsertionSortList {

    /**
     * 对链表进行插入排序
     *
     * @param head
     * @return
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        //lastSorted 为链表的已排序部分的最后一个节点
        //curr 为待插入的元素
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            //维护已经排好序的节点
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(6);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(8);
        ListNode listNode6 = new ListNode(7);
        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        ListNode res = insertionSortList(listNode1);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }

}
