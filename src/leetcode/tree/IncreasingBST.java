package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @since 2021/4/25
 */
public class IncreasingBST {

    /**
     * 中序遍历之后生成新的树
     * 897. 递增顺序搜索树
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);

        TreeNode dummyNode = new TreeNode(-1);
        TreeNode currNode = dummyNode;
        for (int value : res) {
            currNode.right = new TreeNode(value);
            currNode = currNode.right;
        }
        return dummyNode.right;
    }

    public void inorder(TreeNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        inorder(node.left, res);
        res.add(node.val);
        inorder(node.right, res);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        node6.right = node8;
        node8.left = node7;
        node8.right = node9;

        IncreasingBST bst = new IncreasingBST();

        TreeNode res = bst.increasingBST(node5);

        TreeNode curNode = res;
        while (curNode != null) {
            System.out.println(curNode.val);
            curNode = curNode.right;
        }
    }

}
