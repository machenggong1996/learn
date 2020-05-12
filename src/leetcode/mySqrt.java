package leetcode;

/**
 * @author machenggong
 * @date 2020/05/09
 */
public class mySqrt {

    public static int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        int min = 0;
        int max = x;
        //不大于1的话会死循环
        while (max - min > 1) {
            int m = (max + min) / 2;
            if (x / m < m) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(9));
    }

}
