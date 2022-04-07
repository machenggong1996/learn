package leetcode.list;

/**
 * @author machenggong
 * @since 2021/6/4
 */
public class GetIntersectionNode {

    /**
     * 160. 相交链表
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        //两条链表在各自交换一遍之后长度变为一致 最终会在同一个点上 最终在c1相遇
        //a1 a2 c1 c2 c3| b1 b2 b3 c1 c2 c3
        //b1 b2 b3 c1 c2 c3| a1 a2 c1 c2 c3
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(4);
        ListNode a2 = new ListNode(1);
        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(0);
        ListNode b3 = new ListNode(1);
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);

        a1.next = a2;
        a2.next = c1;
        b1.next = b2;
        b2.next = b3;
        b3.next = c1;
        c1.next = c2;
        c2.next = c3;

        ListNode res = getIntersectionNode(a1, b1);
        System.out.println(res.val);
    }

}
