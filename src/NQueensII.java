/**
 * Created by machenggong on 2019/5/13.
 */

/**
 * 回溯算法
 */
public class NQueensII {

    int[] x;//当前解
    int N;//皇后个数
    int sum = 0;//当前已找到的可行方案数

    public int totalNQueens(int n) {
        N = n;
        x = new int[N + 1];
        backTrace(1);
        return sum;
    }

    /**
     * col行这个点，x[col]列这个点，与已经存在的几个皇后，是否符合要求，放到这个位置上，
     * 肯定不在同一行
     * 前半部分为对角线后半部分为同一列
     *
     * @param col
     * @return
     */
    private boolean place(int col) {
        for (int i = 1; i < col; i++) {
            if (Math.abs(col - i) == Math.abs(x[col] - x[i]) || x[col] == x[i]) {
                return false;
            }
        }
        return true;
    }

    private void backTrace(int t) {
        if (t > N) {
            sum++;
        } else {
            //第t行，遍历所有的节点
            for (int j = 1; j <= N; j++) {
                x[t] = j;
                //如果第j个节点可以放下皇后
                if (place(t)) {//在这里递归产生回溯算法 回溯之后t-1 通过for循环进行剪枝
                    //接着放下一个
                    backTrace(t + 1);
                }
            }
        }

    }

    public static void main(String[] args) {
        NQueensII n = new NQueensII();
        System.out.println(n.totalNQueens(4));
    }
}
