package leetcode.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author machenggong
 * @since 2021/5/26
 */
public class ReverseParentheses {

    /**
     * 反转每对括号间的子串
     * @param s
     * @return
     */
    public static String reverseParentheses(String s) {
        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("(u(love)i)"));
    }

}
