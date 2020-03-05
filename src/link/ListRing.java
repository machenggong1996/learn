package link;

/**
 * Created by machenggong on 2020/2/29.
 */
public class ListRing {

    static class Node {

        // 这仅仅是一个标识，用来标记这是哪一个节点
        public int tag;

        public Node next;

        public Node(Node next, int tag) {
            this.next = next;
            this.tag = tag;
        }

        // 逻辑断开 思路需要的tag
        public boolean hasNext = true;
    }


    /**
     * [题目] 一个链表中包含环，请找出该链表的环的入口结点。
     */

    public static void main(String[] args) {

        Node node = createTestNode();
        Node nodeBySlowFast = isCircleBySlowFast(node);
        System.out.println("isCircleBySlowFast : "
                + ((nodeBySlowFast == null) ? "这不是一个环链表" : ("当前环链表的入口是： " + nodeBySlowFast.tag)));

        node = createTestNode();
        Node nodeByHashCode = isCircleByHashCode(node);
        System.out.println("isCircleByHashCode : "
                + ((nodeByHashCode == null) ? "这不是一个环链表" : ("当前环链表的入口是： " + nodeByHashCode.tag)));

        node = createTestNode();
        Node nodeByHasNext = isCircleByHasNext(node);
        System.out.println("isCircleByHasNext : "
                + ((nodeByHasNext == null) ? "这不是一个环链表" : ("当前环链表的入口是： " + nodeByHasNext.tag)));
    }

    public static Node createTestNode() {
        Node node12 = new Node(null, 12);
        Node node11 = new Node(node12, 11);
        Node node10 = new Node(node11, 10);
        Node node9 = new Node(node10, 9);
        Node node8 = new Node(node9, 8);
        Node node7 = new Node(node8, 7);
        Node node6 = new Node(node7, 6);
        Node node5 = new Node(node6, 5);
        Node node4 = new Node(node5, 4);
        Node node3 = new Node(node4, 3);
        Node node2 = new Node(node3, 2);
        // 设置环入口
        node12.next = node5;
        Node head = new Node(node2, 1);
        return head;
    }

    /**
     * 网上做法,其实也是最难的思路:
     * 难度不在于编码，而在于不借助于除了 Node 之外的思路，寻找环的入口 !!
     * 判断是否是有环链表：一个快指针和一个慢指针，在环中，快指针肯定会反向追上慢指针。
     * 寻找环入口：
     * 两个指针，一个从链表头开始走，一个从环内快慢指针相交的节点走；
     * 那么当这两个指针相交的时，此时相交的节点就是环入口。
     * PS:这个寻找环入口的思路，并不容易，甚至于要理解也是有点困难的， 我自己也没有想到方法，只能引用别人的博客中的思路。
     *
     * @param node
     * @return
     */
    public static Node isCircleBySlowFast(Node node) {
        Node slow = node;
        Node fast = node;
        Node p = node;
        while (slow != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                Node q = fast;
                while (p != q) {
                    p = p.next;
                    q = q.next;
                }
                return p;
            }
        }
        return null;
    }

    /**
     * 另外的思路：
     * 如果说 快慢指针的做法是较好的空间复杂度的算法，
     * 其实我一直觉得可以使用一个字符串记录 hashcode 来判断是否重复
     * PS:不过不知道其他语言有没有类似 Java 这种 hashcode 的特性
     *
     * @param node
     * @return
     */
    public static Node isCircleByHashCode(Node node) {
        String hashcodes = "";
        String split = " || ";
        Node temp = node;
        while (temp != null) {
            if (hashcodes.contains(split + temp.hashCode())) {
                return temp;
            }
            hashcodes = hashcodes + split + temp.hashCode();
            temp = temp.next;
        }
        return null;
    }

    /**
     * 其实和 hashcode 是一样的思路：
     * 如果说 hashcode 局限性是并不能确定所有的语言都有类似 hashcode 一样的特性
     * 那么这个方法的思路，则是对于要操作的链表结构允许修改
     * 我在节点类 Node 中添加了 hasNext 属性，这是一种 逻辑断开 的思路：
     * 在我遍历链表的过程中，使用两个指针，因为这是单链不是双链，所以只能用两个指针来处理
     * 两个指针分别是 slow 和fast，slow紧跟着fast，相差一个节点的距离
     * slow 走过节点的时候，会将 hasNext 置为 false，就是告诉你 链表已经断开了
     * 当fast 进入环后，遇到的第一个 逻辑断开 的链表节点就是环的入口
     *
     * @param node
     * @return
     */
    public static Node isCircleByHasNext(Node node) {
        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            if (!fast.next.hasNext) {
                return fast.next;
            }
            slow = fast;
            slow.hasNext = false;
            fast = fast.next;
        }
        return null;
    }

}
