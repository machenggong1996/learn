package leetcode.hot150.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/?envType=study-plan-v2&envId=top-interview-150
 * @author machenggong
 * @since 2025/2/16
 */
public class ZigzagLevelOrder {

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int n = deque.size();
            for (int i = 0; i < n; ++i) {
                if (depth % 2 == 0) {
                    TreeNode e = deque.pollFirst();
                    level.add(e.val);
                    if (e.left != null) {
                        deque.offerLast(e.left);
                    }
                    if (e.right != null) {
                        deque.offerLast(e.right);
                    }
                } else {
                    TreeNode e = deque.pollLast();
                    level.add(e.val);
                    if (e.right != null) {
                        deque.offerFirst(e.right);
                    }
                    if (e.left != null) {
                        deque.offerFirst(e.left);
                    }
                }
            }
            depth++;
            res.add(level);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        List list = zigzagLevelOrder(root);
        list.forEach(o->{
            System.out.println(o);
        });
    }

}
