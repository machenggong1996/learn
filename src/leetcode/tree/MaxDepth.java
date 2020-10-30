package leetcode.tree;

/**
 * @author machenggong
 * @date 2020/07/28
 */
public class MaxDepth {

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {

    }

}
