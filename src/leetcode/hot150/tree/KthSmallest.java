package leetcode.hot150.tree;

import tree.TreeNode;

import java.util.*;

/**
 * 二叉搜索树中第K小的元素
 * URL_ADDRESS * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?favorite=2cktkvj
 *
 * @author machenggong
 * @since 2025/2/15
 */
public class KthSmallest {

    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.data;
    }

    /**
     * 二叉树中序遍历
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.data);
            root = root.right;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(inorderTraversal(root));
        System.out.println(kthSmallest(root, 1));
    }

}
