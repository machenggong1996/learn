package link;

/**
 * Created by mache on 2019/11/8.
 */
public class Link {

    Node head = null; // 头节点

    /**
     * 链表中的节点，data代表节点的值，next是指向下一个节点的引用
     *
     * @author zjn
     */
    class Node {
        Node next = null;// 节点的引用，指向下一个节点
        int data;// 节点的对象，即内容

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 向链表中插入数据
     *
     * @param d
     */
    public void addNode(int d) {
        Node newNode = new Node(d);// 实例化一个节点
        if (head == null) {
            head = newNode;
            return;
        }
        Node tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
    }

    /**
     * @param index:删除第index个节点
     * @return
     */
    public boolean deleteNode(int index) {
        if (index < 1 || index > length()) {
            return false;
        }
        if (index == 1) {
            head = head.next;
            return true;
        }
        int i = 1;
        Node preNode = head;
        Node curNode = preNode.next;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return true;
            }
            //从head向下移动找位置
            preNode = curNode;
            curNode = curNode.next;
            i++;
        }
        return false;
    }

    /**
     * @return 返回节点长度
     */
    public int length() {
        int length = 0;
        Node tmp = head;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        return length;
    }

    /**
     * 在不知道头指针的情况下删除指定节点
     *
     * @param n
     * @return
     */
    public boolean deleteNode11(Node n) {
        if (n == null || n.next == null) {
            return false;
        }
        int tmp = n.data;
        n.data = n.next.data;
        n.next.data = tmp;
        n.next = n.next.next;
        System.out.println("删除成功！");
        return true;
    }

    public void printList() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    /**
     * 链表反转
     * 参考博客 https://blog.csdn.net/qq_36791466/article/details/88766450
     *
     * @param head
     * @return
     */
    public Node ReverseIteratively(Node head) {
        Node pReversedHead = head;
        Node pNode = head;//原指针 当前节点 p2
        Node pPrev = null;//临时指针  指针2：当前节点的前一个节点 p1
        while (pNode != null) {
            // 指针3：当前节点的后一个节点
            Node pNext = pNode.next; //p3
            if (pNext == null) {
                pReversedHead = pNode;
            }
            // 将当前节点的后一个节点指向前一个节点
            pNode.next = pPrev;
            // 将前一个节点指向当前节点
            pPrev = pNode;
            // 将当前节点指向后一个节点
            pNode = pNext;
        }
        this.head = pReversedHead;
        return this.head;
    }

    public static Node reverseListNode(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null) {
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        //下一个节点指针
        Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.next;//nextNode 指向下一个节点
            curNode.next = preNode;//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }

        return preNode;
    }

    public static Node reverseListNodeTest(Node head) {

        Node pre = null;
        Node cur = head;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return null;
    }

    /**
     * 查找单链表的中间节点
     * 定义两个节点，一个快，一个慢
     * 快的一次走两步；慢的一次走一步
     * 当快的走到链表的最后时，慢的刚好走到一半，即链表的中间节点
     *
     * @param head
     * @return
     */
    public Node SearchMid(Node head) {
        Node p = this.head, q = this.head;
        while (p != null && p.next != null && p.next.next != null) {
            p = p.next.next;//快指针走两步
            q = q.next;//慢指针走一步
        }
        System.out.println("Mid:" + q.data);
        return q;
    }


    public static void main(String[] args) {
        Link list = new Link();
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        System.out.println("linkLength:" + list.length());
        System.out.println("head.data:" + list.head.data);
        list.printList();
        list.printList();
        list.ReverseIteratively(list.head);
        list.printList();
        System.out.println("----------------------");
        reverseListNode(list.head);
//        list.printList();
        //list.SearchMid(list.head);
    }


}
