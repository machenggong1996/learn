package leetcode.hot150.tree;

/**
 * https://leetcode.cn/problems/same-tree/?envType=study-plan-v2&envId=top-interview-150
 * 100. 相同的树
 * @author machenggong
 * @since 2025/2/15
 */
public class IsSameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

    }

}
