package suanfa2024;

/**
 * 香槟塔
 * https://leetcode.cn/problems/champagne-tower/
 *
 * @author machenggong
 * @since 2024/10/24
 */
public class ChampagneTower {

    /**
     *
     * @param poured  倒了几杯
     * @param query_row 第几行
     * @param query_glass 第几杯
     * @return
     */
    public static double champagneTower(int poured, int query_row, int query_glass) {
        // 首先将所有的 poured 杯香槟全部倒到 row=0 的这个杯子中
        double[] row = {poured};
        // i是层数
        for (int i = 1; i <= query_row; i++) {
            double[] nextRow = new double[i + 1];
            // j是这一层第几个杯子
            for (int j = 0; j < i; j++) {
                // 上一层有几杯香槟
                double volume = row[j];
                // 杯子满了之后 向他下面的每个杯子倒剩余一半的香槟
                if (volume > 1) {
                    nextRow[j] += (volume - 1) / 2;
                    nextRow[j + 1] += (volume - 1) / 2;
                }
            }
            row = nextRow;
        }
        return Math.min(1, row[query_glass]);
    }

    public static void main(String[] args) {
        System.out.println(champagneTower(4, 3, 2));
    }

}
