package leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by machenggong on 2020/3/22.
 */
public class ValidBST {

    /**
     * 验证二叉搜索树
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    public static boolean isValidBST1(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    public static void main(String[] args) {
        TreeNode node6 = new TreeNode(6);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);

        node6.left = node4;
        node4.left = node2;
        node4.right = node5;
        node6.right = node8;
        node8.left = node7;
        node8.right = node9;

        System.out.println(isValidBST(node6));
        System.out.println(isValidBST1(node6));

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
