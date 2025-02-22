package leetcode.hot150.tree;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/?envType=study-plan-v2&envId=top-interview-150
 *
 * 验证二叉搜索树
 *
 * @author machenggong
 * @since 2025/2/16
 */
public class IsValidBST {

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

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(isValidBST(root));
    }

}
