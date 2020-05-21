package leetcode.offer;

/**
 * @author machenggong
 * @date 2020/05/14
 */
public class CountDigitOne {

    /**
     * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
     * 1到n中一的个数
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        int count = 0;
        long i = 1;        // 从个位开始遍历到最高位
        while (n / i != 0) {
            long high = n / (10 * i);  // 高位
            long cur = (n / i) % 10;   // 当前位
            long low = n - (n / i) * i;
            //分三种情况 当前位是0，1，其他数字 三种不同的计算公式 计算完递推下一位
            if (cur == 0) {
                count += high * i;
            } else if (cur == 1) {
                count += high * i + (low + 1);
            } else {
                count += (high + 1) * i;
            }
            i = i * 10;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(2304));
    }

}
