package netease.hard;

import java.util.Scanner;

/**
 * 7-8 解码异或后的排列
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Main7_8 {

    public static void main(String[] args) {
        try {
            // 输入
            Scanner in = new Scanner(System.in);
            String[] parts = in.nextLine().split(",");
            int[] encoded = new int[parts.length];
            for(int i=0; i<parts.length; i++) {
                encoded[i] = Integer.parseInt(parts[i]);
            }

            // 计算 perm 1-n的数字
            int[] perm = solve(encoded);

            // 输出
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<perm.length; i++) {
                if(i!=0){
                    sb.append(",");
                }
                sb.append(perm[i]);
            }
            System.out.println(sb);

        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    static int[] solve(int[] encoded) {
        // 共n个正整数，all 为它们的异或
        int n = encoded.length+1;
        int all = 1;
        for(int i=2; i<=n; i++){
            all = all ^ i;
        }

        // 取 encoded 下标为奇数的元素，将其和 all 异或，得到 perm[0]
        // perm数组是 1-5 的排列，所以 perm[0] 一定是 1-5 中的一个
        int[] perm = new int[encoded.length+1];
        // 第二关键步骤
        // encoded[0] = perm[0] ^ perm[1]
        //encoded[1] = perm[1] ^ perm[2]
        //encoded[2] = perm[2] ^ perm[3]
        //encoded[3] = perm[3] ^ perm[4]
        //encoded[4] = perm[4] ^ perm[5]
        //encoded[5] = perm[5] ^ perm[6]
        // 第三关键步骤
        // encoded[1] = perm[1] ^ perm[2]
        //encoded[3] = perm[3] ^ perm[4]
        //encoded[5] = perm[5] ^ perm[6]
        for(int i=1; i<encoded.length; i+=2) {
            all = all ^ encoded[i];
        }
        perm[0] = all;

        // 求 perm 其他元素
        for(int i=1; i<perm.length; i++) {
            perm[i] = perm[i-1] ^ encoded[i-1];
        }
        return perm;
    }

}
