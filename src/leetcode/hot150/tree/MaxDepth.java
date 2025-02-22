package leetcode.hot150.tree;

/**
 * @author machenggong
 * @date 2020/07/28
 */
public class MaxDepth {

    /**
     * 二叉树深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public int minDepth(TreeNode root) {
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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxDepth(root));
    }

}
