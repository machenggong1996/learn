package leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @since 2021/6/21
 */
public class ReadBinaryWatch {

    /**
     * 401. 二进制手表
     * @param turnedOn
     * @return
     */
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int h = 0; h < 12; ++h) {
            for (int m = 0; m < 60; ++m) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(readBinaryWatch(1));
    }

}
