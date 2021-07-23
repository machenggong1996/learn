package leetcode.tree;

/**
 * @author machenggong
 * @date 2021/3/12
 * @description
 */
public class IsSubStructure {

    /**
     * 剑指 Offer 26. 树的子结构
     *
     * @param A
     * @param B
     * @return
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    /**
     * 判断树 A 中 以 n_An
     * A
     * 为根节点的子树 是否包含树 B
     *
     * @param A
     * @param B
     * @return
     */
    private static boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    /**
     * 3
     * / \
     * 4   5
     * / \
     * 1   2
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        node3.left = node4;
        node3.right = node5;

        node4.left = node1;
        node4.right = node2;

        TreeNode subNode4 = new TreeNode(4);

        subNode4.left = new TreeNode(1);

        System.out.println(isSubStructure(node3, subNode4));
    }


}
