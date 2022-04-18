package leetcode.offer;

/**
 * @author machenggong
 * @date 2020/06/02
 */
public class SumNums {

    /**
     * 剑指 Offer 64. 求1+2+…+n
     *
     * 求1+2+…+n
     * 不能用乘除法 if while else case
     * 使用递归短路
     *
     * @param n
     * @return
     */
    public static int sumNums(int n) {
        int sum = n;
        boolean flag = n > 0 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumNums(3));
    }

}
