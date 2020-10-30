package leetcode.tree;

/**
 * @author machenggong
 * @date 2020/08/21
 */
public class MinDepth {

    /**
     * 二叉树最小深度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;
    }

    public static int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left != null && root.right != null) {
            return Math.min(minDepth1(root.left), minDepth1(root.right)) + 1;
        }
        if (root.left != null) {
            return minDepth1(root.left) + 1;
        }
        if (root.right != null) {
            return minDepth1(root.right) + 1;
        }
        return 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        //root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(minDepth1(root));
    }
}
