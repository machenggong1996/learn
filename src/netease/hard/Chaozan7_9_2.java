package netease.hard;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 7-9最长超赞子字符串 TODO 还没看懂 输入1 会有20分
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Chaozan7_9_2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (valid(str, i, j)) {
                    result = Math.max(result, j - i + 1);
                }
            }
        }
        System.out.println(result);
    }

    public static boolean valid(String str, int i, int j) {
        HashSet<Character> tmp = new HashSet<>();
        while (i <= j) {
            Character c = str.charAt(i);
            if (tmp.contains(c)) {
                tmp.remove(c);
            } else {
                tmp.add(c);
            }
            i++;
        }
        if (tmp.size() <= 1) {
            return true;
        }
        return false;
    }


}
