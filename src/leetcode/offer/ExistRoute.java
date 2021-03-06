package leetcode.offer;

/**
 * @author machenggong
 * @date 2020/05/28
 */
public class ExistRoute {

    /**
     * 矩阵中的路径
     *
     * @param board
     * @param word
     * @return
     */

    public boolean exist(char[][] board, String word) {
        if (board == null || board[0] == null || board.length == 0 || board[0].length == 0 || word == null || word
                        .equals("")) {
            return false;
        }
        boolean[][] isVisited = new boolean[board.length][board[0].length];
        char[] chs = word.toCharArray();

        //数组中每一个字母都遍历一遍
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chs[0]) {
                    if (bfs(board, i, j, isVisited, chs, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean bfs(char[][] board, int i, int j, boolean[][] isVisited, char[] chs, int index) {

        if (index == chs.length) {
            return true;
        }
        //出现一种情况直接false
        if (i < 0 || j < 0 || i == board.length || j == board[0].length || isVisited[i][j]
                        || board[i][j] != chs[index]) {
            return false;
        }
        isVisited[i][j] = true;
        //上下左右四个方向都可以
        boolean res = bfs(board, i + 1, j, isVisited, chs, index + 1) || bfs(board, i - 1, j, isVisited, chs, index + 1)
                        || bfs(board, i, j + 1, isVisited, chs, index + 1) || bfs(board, i, j - 1, isVisited, chs,
                                                                                  index + 1);
        isVisited[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        ExistRoute existRoute = new ExistRoute();
        char[][] routes = new char[][] { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
        existRoute.exist(routes, "ABCCED");
    }
}
