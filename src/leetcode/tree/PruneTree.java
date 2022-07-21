package leetcode.tree;

/**
 * @author machenggong
 * @since 2022/7/21
 */
public class PruneTree {

    /**
     * 814. 二叉树剪枝
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }

    public static void main(String[] args) {

    }


}
