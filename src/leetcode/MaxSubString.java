package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by machenggong on 2019/6/22.
 */
public class MaxSubString {

    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstringTest(str));
    }

    /**
     * 滑动窗口法
     * 无重复子序列
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring2(String s) {
        //尾部
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        //使用flag作为滑动窗口的标志位
        while (i < s.length()) {
            char a = s.charAt(i);
            //flag开始搜索的位置 pos代表头部
            int pos = s.indexOf(s.charAt(i), flag);
            if (pos < i) {
                if (length > result) {
                    result = length;
                }
                if (result >= s.length() - pos - 1) {
                    return result;
                }
                length = i - pos - 1;
                flag = pos + 1;
            }
            length++;
            i++;
        }
        return length;
    }

    public static int lengthOfLongestSubstring(String s) {
        String res = "";
        int length = s.length();
        if (length < 1) {
            return 0;
        }
        int maxLen = 1;
        for (int head = 0, tail = 1; tail < s.length(); tail++) {
            char c = s.charAt(tail);
            int index = s.indexOf(c, head);
            if (index < tail) {
                head = index + 1;
            }
            int len = tail - head + 1;
            if (len > maxLen) {
                res = s.substring(head, tail + 1);
                System.out.println(res);
            }
            maxLen = len > maxLen ? len : maxLen;
        }
        return maxLen;
    }

    public static int lengthOfLongestSubstringTest(String s) {
        //String s = "pwwkew";
        int maxlen = 0;

        if (s == null || s.length() == 0) {
            return maxlen;
        }
        for (int head = 0, tail = 1; tail < s.length(); tail++) {

            int index = s.indexOf(s.charAt(tail), head);

            if (index < tail) {
                head = index + 1;
            }

            int len = tail - head + 1;

            maxlen = maxlen > len ? maxlen : len;
        }


        return maxlen;
    }

}
