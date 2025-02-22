package leetcode.tree;

/**
 *
 * https://leetcode.cn/problems/3Etpl5/solutions/
 * 从根节点到叶节点的路径数字之和
 *
 * @author machenggong
 * @since 2025/2/14
 */
public class SumNumbers {

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public static int dfs(TreeNode root, int prevSum) {
        if (root == null) {
            return 0;
        }
        int sum = prevSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    public static void main(String[] args) {

    }

}
