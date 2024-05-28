package netease.hard;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * TODO 没看明白
 * 7-5 二叉树最大路径和
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class MaxPathSum7_5_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] strs = in.nextLine().split(",");
        Node node = buildTree(strs);
        node.road();
        node.findMax();
        System.out.print(result);
    }

    public static Node buildTree(String[] strs) {
        Node head = new Node(Integer.parseInt(strs[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int index = 0;
        while (!queue.isEmpty() && index < strs.length - 1) {
            Node curNode = queue.poll();
            Node left = buildNode(++index, strs);
            if (left != null) {
                queue.add(left);
            }
            curNode.left = left;
            Node right = buildNode(++index, strs);
            if (right != null) {
                // 写错了 写成left了
                queue.add(right);
            }
            curNode.right = right;
        }
        return head;
    }

    public static Node buildNode(int i, String[] strs) {
        if (i > strs.length - 1) {
            return null;
        }
        if (strs[i].equals("null")) {
            return null;
        }
        return new Node(Integer.parseInt(strs[i]));
    }

    public static Integer result = Integer.MIN_VALUE;

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public int leftRoadValue;
        public int rightRoadValue;

        public Node(int value) {
            this.value = value;
        }

        public int road() {
            if (left != null) {
                leftRoadValue = left.road();
            } else {
                leftRoadValue = 0;
            }
            if (right != null) {
                rightRoadValue = right.road();
            } else {
                rightRoadValue = 0;
            }
            return Math.max(Math.max(leftRoadValue + value, rightRoadValue + value), value);
        }

        public void findMax() {
            int r1 = leftRoadValue + value + rightRoadValue;
            int r2 = leftRoadValue + value;
            int r3 = value + rightRoadValue;
            int r4 = value;
            int r5 = leftRoadValue;
            int r6 = rightRoadValue;
            if (r1 > result) {
                result = r1;
            }
            if (r2 > result) {
                result = r2;
            }
            if (r3 > result) {
                result = r3;
            }
            if (r4 > result) {
                result = r4;
            }
            if (r5 > result) {
                result = r5;
            }
            if (r6 > result) {
                result = r6;
            }
            if (left != null) {
                left.findMax();
            }
            if (right != null) {
                right.findMax();
            }
        }


    }

}
