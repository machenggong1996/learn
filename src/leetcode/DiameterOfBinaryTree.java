package leetcode;

/**
 * Created by machenggong on 2020/3/10.
 */
public class DiameterOfBinaryTree {

    /**
     * 二叉树直径
     * <p>
     * 1
     * / \
     * 2  3
     * / \
     * 4   5
     */
    static int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        if (root != null) {
            //遍历每一个节点,求出此节点作为根的树的深度,那么,左子树深度加右子树深度的最大值即是答案
            setDepth(root);
            return max;
        }
        return 0;
    }

    public static int setDepth(TreeNode root) {
        if (root != null) {
            int right = setDepth(root.right);
            int left = setDepth(root.left);
            if (right + left > max) {
                max = right + left;
            }
            return Math.max(right, left) + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        treeNode.left = new TreeNode(2);

        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);

        System.out.println(diameterOfBinaryTree(treeNode));
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
