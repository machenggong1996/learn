package suanfa2024;

/**
 * 柠檬水找零
 * https://leetcode.cn/problems/lemonade-change/
 *
 * @author machenggong
 * @since 2024/10/16
 */
public class LemonadeChange {

    // 贪心算法
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                // 贪心 优先使用5和10
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) { // 其次使用3个5
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }

}
