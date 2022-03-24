package leetcode.offer;

import java.util.Stack;

/**
 * @author machenggong
 * @date 2021/3/13
 * @description
 */
public class VerifyPostorder {

    /**
     * 剑指 Offer 33. 二叉搜索树的后序遍历序列
     *
     * @param postorder
     * @return
     */
    public static boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    /**
     * {1,2,5,10,6,9,4,3} 应该遵循 0到1位都比最后一位小 2到6位都比最后一位大 通过这种规则区分出每个子树 继而递归 发现 {5,10,6,9} 这棵子树不满足这个规则 6小于9 这也是下面p==j的含义
     *
     * @param postorder
     * @param i
     * @param j
     * @return
     */
    static boolean recur(int[] postorder, int i, int j) {
        // 说明就一个节点
        if (i >= j) {
            return true;
        }
        int p = i;
        // 寻找最大的值作为根节点
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        // p == j 判断此树是否正确  i到m-1是左子树 m到j-1是右子树 j是根
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    /**
     * 辅助单调栈
     *
     * @param postorder
     * @return
     */
    public static boolean verifyPostorder1(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                root = stack.pop();
            }
            stack.add(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] postorderTrue = new int[]{1, 3, 2, 6, 5};
        int[] postorderFalse = {1, 2, 5, 10, 6, 9, 4, 3};
        System.out.println(verifyPostorder(postorderFalse));
        System.out.println(verifyPostorder1(postorderTrue));
    }


}
