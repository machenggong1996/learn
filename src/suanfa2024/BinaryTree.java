package suanfa2024;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树遍历
 *
 * @author machenggong
 * @since 2024/10/17
 */
public class BinaryTree {

    // 二叉树的层次遍历 使用递归实现
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrderHelper(root, 0, result);
        return result;
    }

    private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        // 如果当前层数已经超过结果列表的大小，说明是新的一层
        if (level >= result.size()) {
            result.add(new ArrayList<>());
        }
        // 将当前节点的值添加到对应层次的列表中
        result.get(level).add(node.val);
        // 递归遍历左子树，层数增加
        levelOrderHelper(node.left, level + 1, result);
        // 递归遍历右子树，层数增加
        levelOrderHelper(node.right, level + 1, result);
    }

    // 层次遍历 迭代实现
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            // 获取当前层的节点个数
            int currentLevelSize = queue.size();
            // 遍历当前层的每个节点
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                // 将下一层的节点加入队列
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            // 将当前层添加到结果列表中
            result.add(level);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        System.out.println(levelOrder1(root));

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
