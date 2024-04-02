package netease.medium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author machenggong
 * @since 2024/4/1
 */
public class ReverseKGroup7_8_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 复用一下代码，这是第一行
        Node initData = Node.init(br.readLine());
        int k = Integer.parseInt(initData.initNextAddress);
        Map<String, Node> map = new HashMap<>();
        for(int i=0; i<Integer.parseInt(initData.data); i++) {
            Node n = Node.init(br.readLine());
            map.put(n.address, n);
        }
        Node head = Node.init(map, initData.address);
        output(head, k);
    }

    public static void output(Node node, int k) {
        // 转换成list
        ArrayList<Node> list = new ArrayList<>();
        while(node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0;
        for(; i*k<=list.size(); i++) {
            if(i==0) continue;
            // 这一段的范围是：(i-1) * k 到 i*k-1
            // 从后到前输出即可
            for(int j=i*k-1; j>=(i-1) * k; j--) {
                String nextAddr; // 先计算next
                if(j != (i-1) * k) { // 普通节点他的next就是他的上一个。
                    nextAddr = list.get(j-1).address;
                } else {
                    // 但是对于这段的第一个比较特殊，存在两个情况：
                    // 3→2→1→6→5→4。1的下一个是下一段的最后一个
                    // 4→3→2→1→5→6。1的下一个是4原本的下一个
                    if((i+1)*k-1 < list.size()) {
                        nextAddr = list.get((i+1)*k - 1).address;
                    } else {
                        nextAddr = list.get(i*k-1).nextAddress();
                    }
                }

                Node n = list.get(j);
                System.out.println(n.address + " " + n.data + " " + nextAddr);
            }

        }

        // 对于最后还有一小段尾巴的，输出一下.如：4→3→2→1→5→6。上面的代码不会走5和6
        for(i = (i-1)*k; i<list.size(); i++) {
            Node n = list.get(i);
            System.out.println(n.output());
        }
    }


    public static class Node {
        public String address;
        public String data;
        public String initNextAddress;
        public Node next;

        public String output() {
            if(next == null) {
                return address + " " + data + " -1";
            }
            return address + " " + data + " " + next.address;
        }

        public String nextAddress() {
            if(next == null) {
                return "-1";
            }
            return next.address;
        }

        public static Node init(Map<String, Node> map, String headAddress) {
            Node node = map.get(headAddress);
            Node head = node;
            while(node != null) {
                Node next = map.get(node.initNextAddress);
                node.next = next;
                node = next;
            }
            return head;
        }
        public static Node init(String line) {
            String[] list = line.split(" ");
            Node n = new Node();
            n.address = list[0];
            n.data = list[1];
            n.initNextAddress = list[2];
            return n;
        }
    }

}
