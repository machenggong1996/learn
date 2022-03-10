package leetcode.tree;

/**
 * @author machenggong
 * @since 2022/3/10
 */
public class BuildTree {

    /**
     * 重建二叉树
     * 根据前序遍历找到根，根据中序遍历找到左右子树，依次递归。归结：根 > 左 > 右
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 前序遍历 index++ 是子树的根节点
     */
    private static int index = 0;

    public static TreeNode helper(int[] inorder, int[] preorder, int begin, int end) {
        if (begin > end) {
            return null;
        }
        //前序遍历找到根
        TreeNode node = new TreeNode(preorder[index]);
        //找到根在中序遍历间的位置 方便分左右
        int mid = 0;
        for (; mid < inorder.length; ++mid) {
            if (inorder[mid] == preorder[index]) {
                break;
            }
        }
        ++index;
        node.left = helper(inorder, preorder, begin, mid - 1);
        node.right = helper(inorder, preorder, mid + 1, end);
        return node;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {

        TreeNode root;

        root = helper(inorder, preorder, 0, inorder.length - 1);

        return root;

    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println(buildTree(preorder, inorder));
    }

}
