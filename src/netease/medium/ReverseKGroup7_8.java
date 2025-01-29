package netease.medium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 7-8 分段翻转链表
 *
 * @author machenggong
 * @since 2024/3/4
 */
public class ReverseKGroup7_8 {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        ListNode newHead = new ListNode(0, 0);
        newHead.next = head;
        ListNode pre = newHead;
        int i = 0;

        while (head != null) {
            i++;
            // 每 K 个元素做一次反转yu
            if (i % k == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
            } else {
                head = head.next;
            }
        }

        return newHead.next;
    }

    /**
     * 链表反转 双指针
     * @param pre
     * @param next
     * @return
     */
    private static ListNode reverse(ListNode pre, ListNode next) {
        ListNode last = pre.next;
        ListNode cur = last.next;

        while (cur != next) {
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }

        return last;
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

    public static void printLinkedList(ListNode head) {
        while (head != null) {
            if(head.next != null){
                System.out.printf("%05d %d %05d%n", head.address, head.data, head.next.address);
            }else {
                System.out.printf("%05d %d %d%n", head.address, head.data, -1);
            }
            head = head.next;
        }
    }

    /**
00100 6 4
00000 4 99999
00100 1 12309
68237 6 -1
33218 3 00000
99999 5 68237
12309 2 33218
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 开始地址
        int address = scanner.nextInt();
        int totalNodes = scanner.nextInt();
        int k = scanner.nextInt();
        List<ListNode> nodeList = new ArrayList<>();
        Map<Integer,Integer> nodeAddressNextAddressMap = new HashMap<>();
        for (int i = 0; i < totalNodes; i++) {
            int nodeAddress = scanner.nextInt();
            int nodeData = scanner.nextInt();
            int nextAddress = scanner.nextInt();
            nodeAddressNextAddressMap.put(nodeAddress, nextAddress);
            nodeList.add(new ListNode(nodeAddress, nodeData));
        }
        Map<Integer, ListNode> addressNodeMap = nodeList.stream().collect(Collectors.toMap(node -> node.address, Function.identity()));
        int preAddress = address;
        ListNode head = addressNodeMap.get(preAddress);
        while (preAddress != -1){
            ListNode pre = addressNodeMap.get(preAddress);
            Integer nextAddress = nodeAddressNextAddressMap.get(pre.address);
            pre.next = addressNodeMap.get(nextAddress);
            preAddress = nextAddress;
        }
        ListNode newHead = reverseKGroup(head, k);
        printLinkedList(newHead);
    }

    public static class ListNode {
        int address;
        int data;
        ListNode next;
        ListNode(int address, int data) {
            this.address = address;
            this.data = data;
        }
    }

}