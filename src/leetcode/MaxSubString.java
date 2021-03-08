package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by machenggong on 2019/6/22.
 */
public class MaxSubString {

    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstringTest(str));
        System.out.println(lengthOfLongestSubstring1(str));
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

            maxlen = Math.max(maxlen, len);
        }


        return maxlen;
    }

    public static int lengthOfLongestSubstring3(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }


}
