package leetcode.tree;

/**
 * Created by machenggong on 2020/3/24.
 */
public class SymmetricTree {

    /**
     * 对称二叉树
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmertric(root.left, root.right);
    }

    private static boolean isSymmertric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSymmertric(t1.left, t2.right) && isSymmertric(t1.right, t2.left);
    }

    public static void main(String[] args) {

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
