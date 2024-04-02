package netease.simple;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 7-6 同构字符串
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class SameStruct7_6 {

    public static boolean isSameStructure(String s1, String s2) {
        return isSameStruct(s1, s2) && isSameStruct(s2, s1);
    }

    public static boolean isSameStruct(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        for(int i = 0; i < s1.length(); i ++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(map.containsKey(c1)) {
                if(c2 != map.get(c1)) {
                    return false;
                }
            } else {
                map.put(c1, c2);
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner in  = new Scanner(System.in);
        String inputStr = in.nextLine();
        String[] arr = inputStr.split(",");
        if(isSameStructure(arr[0], arr[1])) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

}
