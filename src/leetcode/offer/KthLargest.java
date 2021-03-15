package leetcode.offer;

/**
 * @author machenggong
 * @date 2021/3/14
 * @description
 */
public class KthLargest {

    int res, k;

    /**
     * 面试题54. 二叉搜索树的第 k 大节点
     * 二叉搜索树的 中序遍历倒序 为 递减序列
     * 求 “二叉搜索树第 k 大的节点” 可转化为求 “此树的中序遍历倒序的第 k 个节点”。
     *
     * @param root
     * @param k
     * @return
     */
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        if (k == 0) {
            return;
        }
        if (--k == 0) {
            res = root.val;
        }
        dfs(root.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        root.left = node1;
        root.right = node4;
        node1.right = node2;

        KthLargest kthLargest = new KthLargest();
        System.out.println(kthLargest.kthLargest(root, 2));
    }

}
