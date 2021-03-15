package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class GetIntersectionNode {

    /**
     * 剑指 Offer 52. 两个链表的第一个公共节点
     * a+(b−c)=b+(a−c)
     * a是A list长度 b是B list长度
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while (A != B) {
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public static void main(String[] args) {

    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
