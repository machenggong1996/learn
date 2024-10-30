package suanfa2024;

/**
 * 井字游戏
 * https://leetcode.cn/problems/tic-tac-toe-lcci/
 *
 * @author machenggong
 * @since 2024/10/24
 */
public class JingziGameTictactoe {

    public String tictactoe(String[] board) {
        int length = board.length;
        int heng = 0; //横的和
        int zong = 0; //纵的和
        int left = 0; //左斜线
        int right = 0; //右斜线
        boolean flag = false; //记录有没有空格
        for (int i = 0; i < length; i++) {
            heng = 0;
            zong = 0;
            for (int j = 0; j < length; j++) {
                heng = heng + (int) board[i].charAt(j);
                zong = zong + (int) board[j].charAt(i);
                if (board[i].charAt(j) == ' ') flag = true;
            }
            //横纵检查
            if (heng == (int) 'X' * length || zong == (int) 'X' * length) return "X";
            if (heng == (int) 'O' * length || zong == (int) 'O' * length) return "O";
            //两条斜线上的相加
            left = left + (int) board[i].charAt(i);
            right = right + (int) board[i].charAt(length - i - 1);
        }
        //两条斜线检查
        if (left == (int) 'X' * length || right == (int) 'X' * length) return "X";
        if (left == (int) 'O' * length || right == (int) 'O' * length) return "O";
        if (flag) return "Pending";
        return "Draw";
    }

    public static void main(String[] args) {
        JingziGameTictactoe tictactoe = new JingziGameTictactoe();
        System.out.println(tictactoe.tictactoe(new String[]{" XO", " XO", " XO"}));
    }

}
