package suanfa2024;

/**
 * 石子游戏 https://leetcode.cn/problems/stone-game/
 * https://leetcode.cn/problems/predict-the-winner/solutions/395940/yu-ce-ying-jia-by-leetcode-solution/
 * https://www.bilibili.com/video/BV1gC4y1Q7LX?spm_id_from=333.788.videopod.episodes&vd_source=a7d2de7e33a97dde0b7d88af949aa1f4&p=40
 *
 * @author machenggong
 * @since 2024/10/21
 */
public class StoneGame {

    // 预测玩家
    public static boolean predictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }


    public static int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }

    public static boolean predictTheWinner1(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 计算用户A的得分
        int p1 = maxScore(nums, 0, nums.length - 1);
        int p2 = sum - p1;
        return p1 >= p2;
    }


    public static int maxScore(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int scoreStart = 0, scoreEnd = 0;
        if (start - end == 1) {
            scoreStart = nums[start];
            scoreEnd = nums[end];
        }
        if (end - start >= 2) {
            // A和B都是按照最优策略取
            // 用户A取完第一个之后 用户B会留下最小的A 所以要用min
            scoreStart = nums[start] + Math.min(maxScore(nums, start + 2, end), maxScore(nums, start + 1, end - 1));
            scoreEnd = nums[end] + Math.min(maxScore(nums, start + 1, end - 1), maxScore(nums, start, end - 2));
        }
        return Math.max(scoreStart, scoreEnd);
    }

    // 获取 A和B 最大差值分数 正数说明A赢 负数说明B赢
    public static int maxScore1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        //
        int sLeft = arr[l] - maxScore1(arr, l + 1, r);
        int rRight = arr[r] - maxScore1(arr, l, r - 1);
        return Math.max(sLeft, rRight);
    }

    // 向下优化 动态规划 maxScore1(arr, l + 1, r) 存储到dp[arr.length][arr.length]数组
    // 数组[i][j] 表示arr[i...j]的最大差值


    // 递归有很多重复计算
    // 限制条件是 偶数堆 总石子数是奇数
    public static boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        // i代表起始位置 j代表结束位置
        // 起始位置相同的时候差值就是数字本身
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        // 先手减后手差值最大化
        // i代表从左 j代表从右
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                // 最大分数差
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }

    public static boolean predictTheWinner2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }

    // 限制条件是 偶数堆 总石子数是奇数 选对了必赢
    public static boolean stoneGame1(int[] piles) {
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 2, 4, 6};
        System.out.println(predictTheWinner2(arr));
        //System.out.println((predictTheWinner1(arr)));
    }

}
