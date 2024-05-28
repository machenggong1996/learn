package netease.medium;

import java.util.*;

/**
 * @author machenggong
 * @since 2024/4/1
 */
public class ReverseKGroup7_8_3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Node fristLine = new Node(in.nextLine());
        String initAddress = fristLine.address;
        int k = Integer.parseInt(fristLine.nextAddress);
        int n = fristLine.value;
        Map<String, Node> addressNodeMap = new HashMap<>();
        // 这块要用for循环 不能用while(in.hasNext())
        for (int i = 0; i < n; i++) {
            Node node = new Node(in.nextLine());
            addressNodeMap.put(node.address, node);
        }

        List<Node> nodeList = listNode(initAddress, addressNodeMap);
        handle(nodeList, k);

    }

    public static void handle(List<Node> nodeList, int k) {
        int listSize = nodeList.size();
        List<List<Node>> allSubList = new ArrayList<>();
        for (int i = 0; i < listSize; i = i + k) {
            int startIndex = i;
            int endIndex = Math.min(i + k, listSize);
            List<Node> subList = nodeList.subList(startIndex, endIndex);
            if (subList.size() == k) {
                Collections.reverse(subList);
            }
            allSubList.add(subList);
        }
        int i = 0;
        for (List<Node> subList : allSubList) {
            int j = 0;
            for (Node node : subList) {
                // String nextAddress = "-1";
                if (j + 1 < subList.size()) {
                    System.out.println(node.address + " " + node.value + " " + subList.get(j + 1).address);
                } else {
                    // 这块是i+1<allSubList.size()
                    if (i + 1 < allSubList.size()) {
                        System.out.println(node.address + " " + node.value + " " + allSubList.get(i + 1).get(0).address);
                    } else {
                        System.out.println(node.address + " " + node.value + " " + "-1");
                    }

                }

                j++;
            }
            i++;
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

    public static List<Node> listNode(String initAddress, Map<String, Node> nodeMap) {
        List<Node> nodeList = new ArrayList<>();
        // 这块是不等于
        while (!"-1".equals(initAddress)) {
            Node node = nodeMap.get(initAddress);
            nodeList.add(node);
            initAddress = node.nextAddress;
        }
        return nodeList;
    }

}
