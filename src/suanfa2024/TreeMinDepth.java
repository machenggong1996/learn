package suanfa2024;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树最小深度
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/
 *
 * @author machenggong
 * @since 2024/10/16
 */
public class TreeMinDepth {

    // 递归 深度优先搜索
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        // 计算左边深度
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        // 计算右边深度
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        // 加上当前节点深度
        return min_depth + 1;
    }

    // 广度优先搜索
    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<QueueNode> queue = new LinkedList<QueueNode>();
        queue.offer(new QueueNode(root, 1));
        while (!queue.isEmpty()) {
            QueueNode nodeDepth = queue.poll();
            TreeNode node = nodeDepth.node;
            int depth = nodeDepth.depth;
            // 如果左右节点都为空，则返回当前深度 这个深度就是最小深度 左右都没有节点就是最小深度
            if (node.left == null && node.right == null) {
                return depth;
            }
            if (node.left != null) {
                queue.offer(new QueueNode(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.offer(new QueueNode(node.right, depth + 1));
            }
        }

        return 0;
    }

    public static void main(String[] args) {

    }

    static class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }


}
