package leetcode;

import java.util.Stack;

/**
 * Created by machenggong on 2019/5/30.
 */
public class KuoHao {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if (stack.empty()) {
                    return false;
                }
                char c = stack.pop();
                if ((c == '(' && s.charAt(i) != ')') || (c == '[' && s.charAt(i) != ']') || (c == '{' && s.charAt(i) != '}')) {
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

    public static void main(String[] args) {
        System.out.println(isValid("[{}][]"));
    }

}
