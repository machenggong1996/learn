package leetcode;

/**
 * Created by machenggong on 2020/3/13.
 */
public class MajorityElement {


    /**
     * 多数元素
     * 从第一个数开始count=1，遇到相同的就加1，遇到不同的就减1，减到0就重新换个数开始计数，总能找到最多的那个
     * 摩尔投票法,先假设第一个数过半数并设cnt=1；遍历后面的数如果相同则cnt+1，不同则减一，当cnt为0时则更换新
     * 的数字为候选数（成立前提：有出现次数大于n/2的数存在）
     * 当一个数的重复次数超过数组长度的一半，每次将两个不相同的数删除，最终剩下的就是要找的数。
     *
     * @param nums
     * @return
     */

    public static int majorityElement(int[] nums) {
        int count = 1;
        int maj = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    maj = nums[i + 1];
                }
            }
        }
        return maj;
    }

    public static void main(String[] args) {
        int arr[] = new int[]{1, 2, 2, 2, 1};
        System.out.println(majorityElement(arr));
    }

}
