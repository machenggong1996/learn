package dp;

/**
 * Created by mache on 2019/10/29.
 */
public class TowerOfNumbers {

    /**
     * dp[i][j]=Math.max(dp[i-1][j-1], dp[i-1][j]) + n[i][j] 从下往上看
     *
     * @param tower
     */

    public static void tpWalk(int[][] tower) {
        int[][] dpTower = new int[tower.length][];

        dpTower[tower.length - 1] = tower[tower.length - 1];
        for (int i = tower.length - 2; i >= 0; i--) {
            dpTower[i] = tower[i].clone();
            for (int j = 0; j < dpTower[i].length; j++) {
                if (dpTower[i + 1][j] > dpTower[i + 1][j + 1])
                    dpTower[i][j] += dpTower[i + 1][j];
                else {
                    dpTower[i][j] += dpTower[i + 1][j + 1];
                }
            }
        }


        System.out.println("最优路径长度：" + dpTower[0][0]);

        System.out.println("最优路径：");
        int j = 0;//路径节点
        for (int i = 0; i < dpTower.length - 1; i++) {
            if (j < dpTower[i].length - 1 && dpTower[i][j] < dpTower[i][j + 1])
                j = j + 1;
            System.out.print(tower[i][j] + "->");
        }
        if (j < dpTower[dpTower.length - 1].length - 1 && dpTower[dpTower.length - 1][j] < dpTower[dpTower.length - 1][j + 1])
            j = j + 1;
        System.out.println(tower[dpTower.length - 1][j]);

    }

    /**
     * 从上往下看
     *
     * @param n
     * @return
     */
    public static int minNumberInRotateArray(int n[][]) {
        int max = 0;
        int dp[][] = new int[n.length][n.length];
        dp[0][0] = n[0][0];
        for (int i = 1; i < n.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    //如果是第一列，直接跟他上面数字相加
                    dp[i][j] = dp[i - 1][j] + n[i][j];
                } else {
                    //如果不是第一列，比较他上面跟上面左面数字谁大，谁大就跟谁相加，放到这个位置
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + n[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max;
    }

    /**
     * 从下往上看 简单 滚动数组
     *
     * @param n
     * @return
     */
    public static int minNumberInRotateArray3(int n[][]) {
        int[] temp = new int[n.length];
        for (int i = 0; i < n.length; i++) {
            temp[i] = n[n.length - 1][i];
        }
        for (int i = n.length - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                temp[j] = Math.max(temp[j], temp[j + 1]) + n[i][j];
            }
        }
        return temp[0];
    }


    public static void main(String[] args) {
        int[][] tower = {{9}, {12, 15}, {10, 6, 8}, {2, 18, 9, 5}, {19, 7, 10, 4, 16}};
        for (int i = 0; i < tower.length; i++) {
            for (int j = 0; j < tower[i].length - 1; j++) {
                System.out.print(tower[i][j] + " ");
            }

            System.out.println(tower[i][tower[i].length - 1]);
        }
        //tpWalk(tower);
        System.out.println(minNumberInRotateArray3(tower));
    }
}
