package netease.hard;

import java.util.*;

/**
 * @author machenggong
 * @since 2024/4/1
 */
public class MergeList7_1_3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] fristLines = in.nextLine().split(" ");
        Map<String, Node> addressNodeMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(fristLines[2]); i++) {
            String line = in.nextLine();
            Node node = new Node(line);
            addressNodeMap.put(node.address, node);
        }
        List<Node> nodeList1 = listNode(fristLines[0], addressNodeMap);
        List<Node> nodeList2 = listNode(fristLines[1], addressNodeMap);
        if (nodeList1.size() > nodeList2.size()) {
            output(nodeList1, nodeList2);
        } else {
            output(nodeList2, nodeList1);
        }

    }

    public static void output(List<Node> longList, List<Node> shortList) {
        for (int i = 0; i < longList.size(); i++) {
            String nextAddress = "-1";
            if (i + 1 < longList.size()) {
                // 这个位置写错了 写成 longList.get(i + 1).nextAddress
                nextAddress = longList.get(i + 1).address;
            }
            if ((i + 1) % 2 == 0) {
                int bIndex = shortList.size() - (i + 1) / 2;
                if (bIndex > -1) {
                    Node bNode = shortList.get(bIndex);
                    System.out.println(longList.get(i).address + " " + longList.get(i).value + " " + bNode.address);
                    System.out.println(bNode.address + " " + bNode.value + " " + nextAddress);
                    continue;
                }
            }
            System.out.println(longList.get(i).address + " " + longList.get(i).value + " " + nextAddress);
        }
    }

    public static class Node {

        public int value;

        public String address;

        public String nextAddress;

        public Node(String line) {
            String[] lines = line.split(" ");
            this.address = lines[0];
            this.value = Integer.parseInt(lines[1]);
            this.nextAddress = lines[2];
        }
    }

    public static List<Node> listNode(String address, Map<String, Node> addressNodeMap) {
        List<Node> nodeList = new ArrayList();
        while (!"-1".equals(address)) {
            Node node = addressNodeMap.get(address);
            nodeList.add(node);
            address = node.nextAddress;
        }
        return nodeList;
    }

}
