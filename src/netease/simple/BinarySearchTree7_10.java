package netease.simple;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 7-10 判断是不是二叉搜索树
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class BinarySearchTree7_10 {

    /**
     * 20,null,40,34,70,21,null,55,78
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strList = scanner.nextLine().split(",");
        System.out.println(buildTree(strList).valid());
    }

    public static Node buildTree(String[] strList) {
        int rootValue = Integer.parseInt(strList[0]); // 一般不至于无聊到开头就是null的
        Node root = new Node(rootValue);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while (!queue.isEmpty() && index < strList.length) {
            Node currentNode = queue.poll();
            Node left = buildNode(++index, strList);
            Node right = buildNode(++index, strList);
            currentNode.left = left;
            currentNode.right = right;
            if (left != null) queue.add(left);
            if (right != null) queue.add(right);
        }
        return root;
    }

    // 根据这个index的数据构造一个节点
    public static Node buildNode(int index, String[] strList) {
        if (index >= strList.length || "null".equals(strList[index])) return null;
        return new Node(Integer.parseInt(strList[index]));
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }

        public boolean valid() {
            return (left == null || (left.value < value && left.valid())) && (right == null || (right.value > value && right.valid()));
        }
    }

}
