package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by machenggong on 2020/3/12.
 */
public class GenerateParenthesis {

    /**
     * 括号生成
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        generate(res, "", 0, 0, n);
        return res;
    }

    //count1统计“(”的个数，count2统计“)”的个数
    public static void generate(List<String> res, String ans, int count1, int count2, int n) {
        if (count1 > n || count2 > n) {
            return;
        }
        if (count1 == n && count2 == n) {
            res.add(ans);
        }
        //左边括号数量一定是>=右面括号数量 不可能小于右面数量
        if (count1 >= count2) {
            generate(res, ans + "(", count1 + 1, count2, n);
            generate(res, ans + ")", count1, count2 + 1, n);

        }
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }
}
