package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/12
 * @description
 */
public class MirrorTree {

    /**
     * 二叉树镜像
     *
     * @param root
     * @return
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(tmp);
        return root;
    }

    public static void main(String[] args) {

    }

}
