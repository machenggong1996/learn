package leetcode;

import java.util.Stack;

/**
 * Created by machenggong on 2019/5/30.
 */
public class KuoHao {

    /**
     * 括号问题 第一个出现的右边括号一定挨着他的左边括号才是完整括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (stack.empty()) {
                    return false;
                }
                char c = stack.pop();
                if ((c == '(' && s.charAt(i) != ')') || (c == '[' && s.charAt(i) != ']') || (c == '{'
                                && s.charAt(i) != '}')) {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }

    public static boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || c != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid1("][{]}[]"));
    }

}
