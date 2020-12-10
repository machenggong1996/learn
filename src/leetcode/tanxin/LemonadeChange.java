package leetcode.tanxin;

/**
 * @author machenggong
 * @date 2020/12/10
 */
public class LemonadeChange {

    /**
     * 柠檬水找零
     * 模拟 + 贪心
     *
     * @param bills
     * @return
     */
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
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[] { 5, 5, 5, 10, 20 }));
    }

}
