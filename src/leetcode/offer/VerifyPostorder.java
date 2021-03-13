package leetcode.offer;

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
        while (postorder[p] < postorder[j]) {
            p++;
        }
        int m = p;
        while (postorder[p] > postorder[j]) {
            p++;
        }
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }

    public static void main(String[] args) {
        int[] postorder = new int[]{1, 3, 2, 6, 5};
        System.out.println(verifyPostorder(postorder));
    }


}
