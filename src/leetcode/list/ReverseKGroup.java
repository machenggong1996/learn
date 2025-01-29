package leetcode.list;

/**
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/248591/k-ge-yi-zu-fan-zhuan-lian-biao-by-leetcode-solutio/
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/1/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
 *
 * K个一组翻转链表
 * @author machenggong
 * @date 2021/3/18
 * @description
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 1){
            return head;
        }
        // 创建虚节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // 每一组的前缀节点
        ListNode preGroup = dummy;
        while(true){
            // 每一组的开始节点
            ListNode start = preGroup.next;
            // 获取每一组的结束节点
            ListNode end = start;
            for(int i = 0; i < k - 1 && end != null; i++){
                end = end.next;
            }
            // 如果不足k个节点，则停止
            if(end == null) break;
            // 记录下一组的开始节点
            ListNode nextStart = end.next;
            // 断裂与下一组的连接
            end.next = null;
            // 反转当前组
            ListNode currGroup =  reverse(start);
            // 将当前组与前缀节点连接
            preGroup.next = currGroup;
            // 连接下一组，start已经变为end
            start.next = nextStart;
            // 更新下一组的前缀节点
            preGroup = start;
        }
        return dummy.next;
    }

    public ListNode reverseKGroup1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break;
            //确定开始反转的头结点
            ListNode start = pre.next;
            //记录下一个节点，作为下次反转的起始节点，因为当前需要把end.next置空
            ListNode next = end.next;
            // 断开与下一组的连接
            end.next = null;
            //反转链表并将反转好的值给pre.next,
            //因为反转后star刚好变成当前pre的下一个node
            pre.next = reverse(start);
            //将反转好的链表和后续未反转的链接好
            start.next = next;
            //重置start的起始位置开始下一轮的转换
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;
        while(curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ReverseKGroup group = new ReverseKGroup();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode res = group.reverseKGroup1(node1, 3);
        ListNode temp = res;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

}
