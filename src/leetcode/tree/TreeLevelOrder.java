package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author machenggong
 * @date 2020/05/13
 */
public class TreeLevelOrder {

    /**
     * 二叉树层次遍历
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();
            while (count > 0) {
                //出来先添加进去的
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                count--;
            }
            res.add(list);
        }
        return res;
    }

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();  //存放结果
        Queue<TreeNode> queue = new LinkedList<TreeNode>();   //辅助队列
        if (root != null) {
            //根节点入队
            queue.offer(root);
        }
        //队列不为空，执行循环
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);     //将队列元素输出

            //如果有左节点，就把左节点加入
            if (node.left != null) {
                queue.offer(node.left);
            }
            //如果有右节点，就把右节点加入
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    /**
     * 3
     * / \
     * 9  20
     * /  \
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
        System.out.println(levelOrder(root));
        System.out.println(PrintFromTopToBottom(root));
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
