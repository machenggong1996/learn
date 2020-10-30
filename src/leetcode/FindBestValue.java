package leetcode;

/**
 * @author machenggong
 * @date 2020/06/14
 */
public class FindBestValue {

    /**
     * 转变数组后最接近目标值的数组和
     *
     * @param arr
     * @param target
     * @return
     */
    public static int findBestValue(int[] arr, int target) {
        int n = arr.length;
        int avg = target / n;
        // 记录前一轮的差值
        int pre = Integer.MAX_VALUE;
        // 从平均值开始遍历
        for (int i = avg; ; ++i) {
            // 记录每轮的总和
            int sum = 0;
            //每次计算的都是最小值 所以绝对值会逐步减小
            for (int value : arr) {
                sum += Math.min(value, i);
            }
            // 比较差值，看前一个点是否是最小值
            if (Math.abs(sum - target) >= pre) {
                return i - 1;
            }
            // 更新差值记录
            pre = Math.abs(sum - target);
        }
    }

    public static void main(String[] args) {
        System.out.println(findBestValue(new int[] { 2, 3, 5 }, 10));
    }

}
