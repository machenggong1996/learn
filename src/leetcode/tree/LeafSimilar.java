package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @since 2021/5/10
 */
public class LeafSimilar {

    /**
     * 872. 叶子相似的树
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> seq1 = new ArrayList<Integer>();
        if (root1 != null) {
            dfs(root1, seq1);
        }

        List<Integer> seq2 = new ArrayList<Integer>();
        if (root2 != null) {
            dfs(root2, seq2);
        }

        return seq1.equals(seq2);
    }

    public static void dfs(TreeNode node, List<Integer> seq) {
        if (node.left == null && node.right == null) {
            seq.add(node.val);
        } else {
            if (node.left != null) {
                dfs(node.left, seq);
            }
            if (node.right != null) {
                dfs(node.right, seq);
            }
        }
    }


    public static void main(String[] args) {

    }


}
