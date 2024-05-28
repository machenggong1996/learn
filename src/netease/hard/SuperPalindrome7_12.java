package netease.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;


/**
 * 7-12 超级回文数
 * https://leetcode.cn/problems/super-palindromes/solutions/1/chao-ji-hui-wen-shu-by-leetcode/
 *
 * @author machenggong
 * @since 2024/3/5
 */
public class SuperPalindrome7_12 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String[] parts = sc.nextLine().split(",");
            long L = Long.parseLong(parts[0]);
            long R = Long.parseLong(parts[1]);

            String results = solve3(L, R);
            System.out.println(results);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    public static String solve3(long L, long R) {
        List<String> list = new ArrayList<>();
        for(long i = 0;i<R;i++){
            long i2 = i*i;
            if(i2 < L){
                continue;
            }
            if (i2 > R) break;
            if(isHuiwen(i)&&isHuiwen(i2)){
                list.add(String.valueOf(i2));
            }

        }
        return String.join(",",list);
    }

    public static boolean isHuiwen(long num){
        String str = String.valueOf(num);
        int i = 0;
        int j = str.length() - 1;
        while(i<j){
            if(str.charAt(i) == str.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }

    public static String solve2(long L, long R) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (long i = 0; i < R; i++) {
            long i2 = i * i;
            if (i2 < L) continue;
            if (i2 > R) break;
            if (check(i) && check(i2)) {
                sb.append(i2 + ", ");
            }
        }
        String result = sb.toString();
        result = result.substring(0, result.length() - 2);
        result = result + "]";
        return result;
    }


    // 是不是回文数
    static boolean check(long n) {
        String s = Long.toString(n);
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

}
