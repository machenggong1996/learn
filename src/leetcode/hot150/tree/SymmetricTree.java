package leetcode.hot150.tree;

/**
 * 101. 对称二叉树
 * https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&envId=top-interview-150
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
        return isSymmetric(root.left, root.right);
    }

    private static boolean isSymmetric(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
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
