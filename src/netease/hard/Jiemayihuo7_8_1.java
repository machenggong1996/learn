package netease.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/4/20
 */
public class Jiemayihuo7_8_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 错在逗号 TODO
        String[] strs = in.nextLine().split(",");
        int[] encoded = new int[strs.length];
        for(int i = 0;i<strs.length;i++){
            encoded[i] = Integer.parseInt(strs[i]);
        }
        int[] perm = handle(encoded);
        List<String> list = new ArrayList<>();
        for(int i = 0;i<perm.length;i++){
            list.add(String.valueOf(perm[i]));
        }
        System.out.print(String.join(",",list));
    }

    public static int[] handle(int[] encoded){
        int all = 1;
        for(int i = 2;i<=encoded.length+1;i++){
            all = all ^ i;
        }
        int[] perm = new int[encoded.length+1];
        for(int i = 1;i<encoded.length;i+=2){
            all = all ^ encoded[i];
        }
        perm[0] = all;
        for(int i = 1;i<perm.length;i++){
            perm[i] = perm[i-1]^encoded[i-1];
        }
        return perm;
    }

}
