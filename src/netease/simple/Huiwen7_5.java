package netease.simple;

import java.util.*;

/**
 * 7-5 检测回文字符串
 *
 * @author machenggong
 * @since 2024/2/26
 */
public class Huiwen7_5 {

    public static void main(String[] args) throws Exception {
        Scanner in  = new Scanner(System.in);
        String input  = in.nextLine();

        input = input.replaceAll("[^a-z^A-Z^0-9]", "").toLowerCase();
        StringBuilder builder = new StringBuilder(input);
        if(input.equals(builder.reverse().toString())) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

}
