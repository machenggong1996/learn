package leetcode;

/**
 * Created by machenggong on 2019/6/3.
 */
public class TwoNumberAdd {

    /**
     * 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode resNode = new ListNode(0);
        ListNode resNext = resNode;
        int carry = 0;

        while (l1 != null || l2 != null) {

            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            int val = sum < 10 ? sum : sum - 10; //用判断和减法代替求余、除法能提高性能
            carry = sum < 10 ? 0 : 1;

            resNext.next = new ListNode(val);
            resNext = resNext.next;
        }

        if (carry != 0) {
            resNext.next = new ListNode(1);
        }

        return resNode.next;
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

        ListNode listNode = addTwoNumbers1(listNode1, listNode4);

        System.out.println(listNode);

    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode resNode = new ListNode(0);
        ListNode node = resNode;

        int carry = 0;
        while (l1 != null || l2 != null) {

            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            if (sum >= 10) {
                carry = 1;
                node.next = new ListNode(sum - 10);
            } else {
                carry = 0;
                node.next = new ListNode(sum);
            }
            node = node.next;
        }
        if (carry != 0) {
            resNode.next = new ListNode(1);
        }

        return resNode.next;
    }

}
