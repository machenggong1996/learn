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
        play(0);
    }

    // 皇后/棋盘的个数
    private static final int QUEEN_NUM = 4;

    // 首先定义一个8 * 8 的棋盘
    private static final int[][] Checkerboard = new int[QUEEN_NUM][QUEEN_NUM];

    // 定义一共有多少种放置皇后的算法
    private static int COUNT = 0;

    /**
     * 打印棋盘
     */
    public static final void show() {
        System.out.println("第" + (++COUNT) + "次摆法");
        for (int i = 0; i < QUEEN_NUM; i++) {
            for (int j = 0; j < QUEEN_NUM; j++) {
                System.out.print(Checkerboard[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 判断当前位置是否能放置皇后
     *
     * @param row 行数
     * @param col 列数
     * @return
     */
    public static final boolean check(int row, int col) {

        // 判断当前位置的上面是否有皇后
        for (int i = row - 1; i >= 0; i--) {
            if (Checkerboard[i][col] == 1)
                return false;
        }

        // 判断左上是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (Checkerboard[i][j] == 1)
                return false;
        }

        // 判断右上是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < QUEEN_NUM; i--, j++) {
            if (Checkerboard[i][j] == 1)
                return false;
        }

        return true;
    }

    /**
     * 从第n行放置皇后
     *
     * @param row
     */
    public static final void play(int row) {
        // 遍历当前行的所有单元格 以列为单元
        for (int i = 0; i < QUEEN_NUM; i++) {
            // 是否能够放置皇后
            if (check(row, i)) {
                Checkerboard[row][i] = 1;

                if (row == QUEEN_NUM - 1) {
                    // 最后行 放置完毕 打印皇后
                    show();
                } else {
                    // 放置下一行
                    play(row + 1);
                }

                //回退到当前步骤，把皇后设置为0
                Checkerboard[row][i] = 0;
            }

        }
    }
}
