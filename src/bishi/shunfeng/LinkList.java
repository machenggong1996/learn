package bishi.shunfeng;


/**
 * @author machenggong
 * @date 2021/3/15
 * @description
 */
public class LinkList {


    /**
     * select s.student_id,s.name,m.score, from student s left join
     *
     * (
     *
     * select m1.id,m1.student_id,m1.score,count(m2.score) as rank from math m1
     * join
     * (select distinct score as rank from math) m2
     *
     * where m1.score <= m2.score
     *
     * )
     *
     * M on s.student_id = M.student_id
     *
     *
     * select distinct(score) from math
     *
     * 1  1   91    91
     * 2  2   91    90
     * 3  3   90    89
     * 4  4   89
     *
     */


    static class ListNode {
        ListNode next;
        int val;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode r(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;

        ListNode curNode = head;

        ListNode nextNode = null;
        // 1->2->3
        // 1<-2<-3
        while (curNode != null) {
            nextNode = curNode.next;
            curNode.next = pre;
            pre = curNode;
            curNode = nextNode;
        }
        return pre;
    }

    public static int jump(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int a = 1;
        int b = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = a+b;
            a = b;
            b = res;
        }
        return res;
    }

    public static int numWays(int n) {
        if(n==0){
            return 1;
        }
        if(n==1||n==2){
            return n;
        }
        int x1 = 1;
        int x2 = 2;
        for(int i = 3;i<=n;i++){
            int temp = x1;
            x1 = x2;
            x2 = (temp+x1);
        }
        return x2;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        ListNode res = r(node1);
        ListNode cur = res;
//        while (cur != null) {
//            System.out.println(cur.val);
//            cur = cur.next;
//        }
        System.out.println(jump(50));
        System.out.println(numWays(50));

    }

}
