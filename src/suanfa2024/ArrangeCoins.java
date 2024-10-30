package suanfa2024;

/**
 * 排列硬币
 *
 * https://leetcode.cn/problems/arranging-coins/solutions/1038396/pai-lie-ying-bi-by-leetcode-solution-w52c/?envType=study-plan-v2&envId=binary-search
 *
 * @author machenggong
 * @since 2024/10/12
 */
public class ArrangeCoins {

    public static int arrangeCoins(int n) {
        // i是行数及每行硬币数
        int coins = n;
        for (int i = 1; i <= n; i++) {
            // 剩余硬币数
            coins = coins - i;
            if(coins < i + 1){
                return i;
            }
        }
        return 0;
    }

    // 二分查找 total= k×(k+1)/2
    public static int arrangeCoins1(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 数学方法 一元二次方程的解是 根号下8n+1/2-1/2
    public static int arrangeCoins2(int n) {
        return (int) ((Math.sqrt((long) 8 * n + 1) - 1) / 2);
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins1(8));
    }

}
