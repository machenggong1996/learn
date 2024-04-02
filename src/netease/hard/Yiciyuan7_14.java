package netease.hard;

import java.util.Scanner;

/**
 * @author machenggong
 * @since 2024/3/13
 */
public class Yiciyuan7_14 {
    public static void main(String[] args) {
        // 求解最长回文子串长度 需要判断奇数偶数
        // 纯数字不行
        try {
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                int result = solve(line);
                System.out.println(result);
            }

            // System.out.println(solve("00A00"));
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    static int solve(String line) {
        int max = 0;
        // 中心扩展法判断回文串
        for (int i = 0; i < line.length(); i++) {
            // 长度为奇数，中心位置为 i
            {
                int l = i;
                int r = i;
                int length = 1; // 子串 [i, j] 的长度（含边界）
                boolean containsLetter = false;
                while (l >= 0 && r < line.length()) {
                    if (line.charAt(l) != line.charAt(r)) {
                        break;
                    }
                    // 当前子串是回文串
                    if (!containsLetter) {
                        char c = line.charAt(l);
                        containsLetter = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
                    }
                    if (containsLetter) {
                        max = Math.max(max, length);
                    }
                    // 扩展到下一个子串
                    l--;
                    r++;
                    length += 2;
                }
            }
            // 长度为偶数，中心位置为 i 和 i+1（同上，只是中心位置不同）
            {
                int l = i;
                int r = i + 1;
                int length = 2; // 子串 [i, j] 的长度（含边界）
                boolean containsLetter = false;
                while (l >= 0 && r < line.length()) {
                    if (line.charAt(l) != line.charAt(r)) {
                        break;
                    }

                    // 当前子串是回文串
                    if (!containsLetter) {
                        char c = line.charAt(l);
                        containsLetter = (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
                    }
                    if (containsLetter) {
                        max = Math.max(max, length);
                    }
                    // 扩展到下一个子串
                    l--;
                    r++;
                    length += 2;
                }
            }
        }
        if (max < 3) {
            max = 0;
        }

        return max;
    }

}
