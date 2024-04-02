package netease.hard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO 没看明白
 * 7-5 二叉树最大路径和
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class MaxPathSum7_5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = br.readLine().split(",");
        Node root = buildTree1(strs);
        root.asRoad();
        root.findMax();
        System.out.println(result);
    }

    private static Node buildTree1(String[] strs) {
        Node root = new Node(Integer.parseInt(strs[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int index = 0;
        while (!queue.isEmpty() && index < strs.length - 1) {
            Node currentNode = queue.poll();
            // 末尾可能有null
            index++;
            if (index >= strs.length) {
                break;
            }
            String left = strs[index];
            currentNode.left = "null".equals(left) ? null : new Node(Integer.parseInt(left));
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            index++;
            if (index >= strs.length) {
                break;
            }
            String right = strs[index];
            currentNode.right = "null".equals(right) ? null : new Node(Integer.parseInt(right));
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return root;
    }

    public static int result = Integer.MIN_VALUE;

    public static class Node {
        int value;
        Node left;
        Node right;
        int leftRoad;
        int rightRoad;

        public Node(int v) {
            value = v;
            left = null;
            right = null;
        }

        public int asRoad() {
            if (left != null) {
                leftRoad = left.asRoad();
            } else {
                leftRoad = 0;
            }
            if (right != null) {
                rightRoad = right.asRoad();
            } else {
                rightRoad = 0;
            }
            return Math.max(Math.max(leftRoad + value, rightRoad + value), value);
        }

        public void findMax() {
            // 左 + 右 + 自己
            // 左 + 自己
            // 右 + 自己
            // 自己
            // 左
            // 右
            int r1 = leftRoad + value + rightRoad;
            int r2 = leftRoad + value;
            int r3 = rightRoad + value;
            int r4 = value;
            int r5 = leftRoad;
            int r6 = rightRoad;
            if (result < r1) result = r1;
            if (result < r2) result = r2;
            if (result < r3) result = r3;
            if (result < r4) result = r4;
            if (result < r5) result = r5;
            if (result < r6) result = r6;
            if (left != null) left.findMax();
            if (right != null) right.findMax();
        }
    }

}
