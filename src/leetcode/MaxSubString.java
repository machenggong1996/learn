package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by machenggong on 2019/6/22.
 */
public class MaxSubString {

    public static void main(String[] args) {
        String str = "abac";
        System.out.println(lengthOfLongestSubstring2(str));
    }

    /**
     * 滑动窗口法
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
        int i = 0;
        int flag = 0;
        int length = 0;
        int result = 0;
        //使用flag作为滑动窗口的标志位
        while (i < s.length()) {
            int a = s.charAt(i);
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
}
