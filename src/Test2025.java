import tree.TreeNode;

import java.util.*;

/**
 * @author machenggong
 * @since 2025/2/10
 */
public class Test2025 {

    public static void test(TreeNode root) {
        Queue<TreeNode> quene = new LinkedList<>();
        quene.add(root);
        List<Integer> res = new ArrayList<>();
        while (!quene.isEmpty()) {
            TreeNode node = quene.poll();
            res.add(node.data);
            if (node.left != null) {
                quene.add(node.left);
            }
            if (node.right != null) {
                quene.add(node.right);
            }
        }
    }

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
                    level.add(e.data);
                    if (e.left != null) {
                        deque.offerLast(e.left);
                    }
                    if (e.right != null) {
                        deque.offerLast(e.right);
                    }
                } else {
                    TreeNode e = deque.pollLast();
                    level.add(e.data);
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
        // 生成一个满二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        List<List<Integer>> res = zigzagLevelOrder(root);
        System.out.println(res);
    }

}
