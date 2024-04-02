package netease.hard;

import java.util.Scanner;

/**
 * 7-10 统计美丽子字符串 II
 *
 * @author machenggong
 * @since 2024/3/6
 */
public class CountBeautifulString7_10 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String[] parts = sc.nextLine().split(",");
            String s = parts[0].substring(5, parts[0].length() - 1);
            int k = Integer.parseInt(parts[1].substring(5, parts[1].length()));

            int count = solve(s, k);
            System.out.println(count);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    static int solve(String s, int k) {
        // System.out.println("s="+s+", k="+k);

        // 元素 [i][0]、[i][1] 表示截至 s[i]，元音和辅音的数目
        int[][] counts = new int[s.length()][2];
        if (isVow(s.charAt(0))) {
            counts[0][0] = 1;
        } else {
            counts[0][1] = 1;
        }

        for (int i = 1; i < s.length(); i++) {
            if (isVow(s.charAt(i))) {
                // 元音
                counts[i][0] = counts[i - 1][0] + 1;
                counts[i][1] = counts[i - 1][1];
            } else {
                // 辅音
                counts[i][0] = counts[i - 1][0];
                counts[i][1] = counts[i - 1][1] + 1;
            }
        }
        // 计算子串 [i,j]（含）
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                int vowCount;
                int conCount;
                if (i == 0) {
                    vowCount = counts[j][0];
                    conCount = counts[j][1];
                } else {
                    // 举例：abba 这里计算到bba ba 这些字符串
                    vowCount = counts[j][0] - counts[i - 1][0];
                    conCount = counts[j][1] - counts[i - 1][1];
                }

                boolean isValid = vowCount == conCount && (long) vowCount * (long) conCount % k == 0; // 注：转为 long，以防溢出
                if (isValid) {
                    count++;
                }
            }
        }

        return count;
    }

    static boolean isVow(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

}
