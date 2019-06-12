package leetcode;

/**
 * Created by machenggong on 2019/6/3.
 */
public class TwoNumberAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

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

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
