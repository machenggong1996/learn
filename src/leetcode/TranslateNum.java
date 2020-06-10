package leetcode;

/**
 * @author machenggong
 * @date 2020/06/09
 */
public class TranslateNum {

    public static int translateNum(int num) {
        String src = String.valueOf(num);
        //p前指针 q当前指针 r结果
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q;
            q = r;
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        System.out.println(translateNum(12258));
    }

}
