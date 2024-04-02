package netease.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author machenggong
 * @since 2024/4/1
 */
public class MergeList7_1_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line0 = br.readLine().split(" ");
        Map<String, Node> nodeMap = new HashMap<>();
        for(int i=0; i<Integer.parseInt(line0[2]); i++) {
            Node n = Node.valueOf(br.readLine());
            nodeMap.put(n.address, n);
        }

        List<Node> list1 = Node.buildList(line0[0], nodeMap);
        List<Node> list2 = Node.buildList(line0[1], nodeMap);
        if(list1.size() < list2.size()) {
            output(list1, list2);
        } else {
            output(list2, list1);
        }


    }

    public static void output(List<Node> shortList, List<Node> longList) {
        for(int i=0; i<longList.size(); i++) {
            Node n = longList.get(i);
            String nNextAddr = "-1";
            if(i + 1 < longList.size()) {
                nNextAddr = longList.get(i+1).address;
            }
            // 每隔两个元素
            if((i+1) % 2 == 0) {
                // 倒序
                int bIndex = shortList.size() - (i+1) / 2;
                if(bIndex > -1) {
                    Node b = shortList.get(bIndex);
                    System.out.println(n.address + " " + n.data + " " + b.address);
                    System.out.println(b.address + " " + b.data + " " + nNextAddr);
                    continue;
                }
            }
            System.out.println(n.address + " " + n.data + " " + nNextAddr);
        }
    }
    public static class Node {
        String address;
        String data;
        String intitNextAddr;
        Node next;

        public static Node valueOf(String line) {
            Node n = new Node();
            String[] vars = line.split(" ");
            n.address = vars[0];
            n.data = vars[1];
            n.intitNextAddr = vars[2];
            return n;
        }

        public static List<Node> buildList(String addr, Map<String, Node> nodeMap) {
            List<Node> list = new ArrayList<>(nodeMap.values().size());
            while(!"-1".equals(addr)) {
                Node n = nodeMap.get(addr);
                list.add(n);
                addr = n.intitNextAddr;
            }
            return list;
        }
    }

}
