package leetcode.tanxin;

/**
 * @version 1.0
 * @author: machenggong
 * @date: 2020-03-26 8:56
 * @description:
 */
public class CanCompleteCircuit {

    /**
     * 加油站 贪心算法 leetcode 134
     *
     * @param gas
     * @param cost
     * @return
     */
    /**
     * 思路: 排除所有不能作为起点加油站, 剩下的就是可以作为起点的加油站
     * <p>
     * 假设: [a___b____c___]
     * 为什么从 a到 b时currTank < 0,则[a,b)的所有节点都不能作为起始节点 ?
     * 因为,经过[a,b-1]中间的所有节点后,剩余油量最多的就是a作为起始节点的时候, 在剩余油量最多的情况下都无法到达b站,那么在油量较少的情况下更不可能到达;
     * 首先, [a,a+1]的剩余油量一定大于0,①如果i(i为起点)到j的剩余油量小于0,那么i不能作为起始站; ②如果i(i为起点)到j的剩余油量大于等于0,那么从a到i的油量也一定大于等于0,
     * 那么还是以a作为起始站剩余油量最多, 所以i还是不能作为起始站; (a < i < j < b)
     * <p>
     * 在逐步排除不能作为起点的加油站后, 剩余的加油站就是起始点
     *
     * 只要获取减消耗大于等于0 一定有位置可以行驶一周
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        //获取的油箱减去消耗的油箱大于等于0
        int total_tank = 0;
        /**
         * currTank: startStation作为起始站,第i+1个加油站后剩余的油量
         * 在这个过程中, 从某一个站到达下一个站后剩余的油量一定大于0, 否则, 将重置起始站;
         * 如果从startStation站不能到达i站, 那么startStation与i之间的任意一站都不能到达i+1站;
         */
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;
    }

    public static void main(String[] args) {
        System.out.println(canCompleteCircuit(new int[] { 2, 4, 4 }, new int[] { 3, 4, 3 }));
    }

}
