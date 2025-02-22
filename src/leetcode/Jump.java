package leetcode;

/**
 * https://leetcode.cn/problems/jump-game-ii/solutions/230241/tiao-yue-you-xi-ii-by-leetcode-solution/
 * 跳跃游戏2
 * @author machenggong
 * @since 2024/12/13
 */
public class Jump {

    /**
     * 反向查找出发位置
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * 方法二：正向查找可到达的最大位置
     *
     * @param nums
     * @return
     */
    public static int jump1(int[] nums) {
        int length = nums.length;
        // 能跳的边界
        int end = 0;
        // 能跳的最远位置
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 到达边界，就更新边界，并且步数加一
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(jump1(nums));
    }

}
