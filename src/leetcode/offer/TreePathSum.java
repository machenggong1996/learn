package leetcode.offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author machenggong
 * @since 2022/3/25
 */
public class TreePathSum {

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     *
     * @param root
     * @param target
     * @return
     */
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target);
        return ret;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        // 存储结果到最后
        path.offerLast(root.val);
        // 目标分和当前节点作差
        target -= root.val;
        // 是子节点并且目标分已经被减为0
        if (root.left == null && root.right == null && target == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        // 移除最后一个
        path.pollLast();
    }

    /**
     * 3
     * / \
     * 9  20
     * __/  \
     * 15   7
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        TreePathSum treePathSum = new TreePathSum();
        System.out.println(treePathSum.pathSum(root, 30));
    }

}
