package leetcode.tanxin;

/**
 * Created by machenggong on 2020/3/24.
 */
public class JumpGame {

    /**
     * 跳跃游戏 贪心算法
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;//向前移位置
            }
        }
        return lastPos == 0;
    }

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }

}
