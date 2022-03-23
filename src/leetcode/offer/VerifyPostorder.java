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

    static boolean recur(int[] postorder, int i, int j) {
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
        // p == j 判断此树是否正确
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
        int[] postorder = new int[]{1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(postorder));
    }


}
