package netease.medium;

import java.util.Scanner;
import java.util.Stack;

/**
 * 7-3 删除无效的括号
 *
 * @author machenggong
 * @since 2024/2/28
 */
public class MinRemoveToMakeValid7_3 {

    public static String minRemoveToMakeValid(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 栈里存放的是需要删除的括号的下标 上次写成stack.push(i);导致错误
                stack.push(result.length());
                result.append(c);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                    result.append(c);
                }
            } else {
                result.append(c);
            }
        }

        // a()()((
        while (!stack.isEmpty()) {
            int index = stack.pop();
            result.deleteCharAt(index);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(minRemoveToMakeValid(input)); // 输出 "lee(t(c)o)de"
    }

}
