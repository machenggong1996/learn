package leetcode.tanxin;

/**
 * @author machenggong
 * @date 2020/12/15
 * @description
 */
public class MonotoneIncreasingDigits {

    /**
     * 单调递增的数字
     *
     * @param N
     * @return
     */
    public static int monotoneIncreasingDigits(int N) {
        char[] strN = Integer.toString(N).toCharArray();
        int i = 1;
        //寻找单调递增的结束位置
        while (i < strN.length && strN[i - 1] <= strN[i]) {
            i += 1;
        }
        if (i < strN.length) {
            //最后一位递增减1
            while (i > 0 && strN[i - 1] > strN[i]) {
                strN[i - 1] -= 1;
                i -= 1;
            }
            //递增后面的数字变为9
            for (i += 1; i < strN.length; ++i) {
                strN[i] = '9';
            }
        }
        return Integer.parseInt(new String(strN));
    }

    public static void main(String[] args) {
        System.out.println(monotoneIncreasingDigits(342));
    }

}
