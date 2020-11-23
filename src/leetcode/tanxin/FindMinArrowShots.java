package leetcode.tanxin;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/11/23
 */
public class FindMinArrowShots {

    /**
     * 452. 用最少数量的箭引爆气球
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        //按照横坐标结束排序 将 points 按照 y 值（右边界）进行升序排序
        Arrays.sort(points, (point1, point2) -> {
            if (point1[1] > point2[1]) {
                return 1;
            } else if (point1[1] < point2[1]) {
                return -1;
            } else {
                return 0;
            }
        });
        int pos = points[0][1];
        int ans = 1;
        for (int[] balloon : points) {
            if (balloon[0] > pos) {
                pos = balloon[1];
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //数组point0为横坐标开始 point1为横坐标结束
        System.out.println(findMinArrowShots(new int[][] { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } }));
    }

}
