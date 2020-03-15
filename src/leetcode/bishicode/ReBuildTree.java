package leetcode.bishicode;

/**
 * Created by machenggong on 2020/3/14.
 */
public class ReBuildTree {

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

    private static int index = 0;

    public static TreeNode helper(int[] inorder, int[] preorder, int begin, int end) {
        if (begin > end) {
            return null;
        }

        //前序遍历找到根
        TreeNode node = new TreeNode(preorder[index]);

        //找到根在中序遍历间的位置
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
        int[] preorder = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = new int[]{4, 7, 2, 1, 5, 3, 8, 6};
        System.out.println(buildTree(preorder, inorder));
    }

}
