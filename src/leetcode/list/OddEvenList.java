package leetcode.list;

/**
 * @author machenggong
 * @date 2020/11/13
 */
public class OddEvenList {

    /**
     * 奇偶链表
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode evenHead = head.next;
        //维护两个节点，odd指向奇数位置节点，even指向偶数位置节点
        //最终head节点存储奇数节点 even节点存储偶数节点
        ListNode odd = head, even = evenHead;
        /*
         * 更新奇数节点时，奇数节点的后一个节点需要指向偶数节点的后一个节点，因此令 odd.next = even.next，然后令 odd = odd.next，此时 odd 变成 even 的后一个节点。
         * 更新偶数节点时，偶数节点的后一个节点需要指向奇数节点的后一个节点，因此令 even.next = odd.next，然后令 even = even.next，此时 even 变成 odd 的后一个节点。
         */
        while (even != null && even.next != null) {
            odd.next = even.next;
            //自身赋值操作odd地址会变化 head地址不会变化
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode resNode = oddEvenList(node1);

    }

}
