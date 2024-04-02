package netease.hard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 7-9最长超赞子字符串 TODO 还没看懂 输入1 会有20分
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Chaozan7_9 {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            int result = longestAwesome(line);
            System.out.println(result);
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * 这算法的思路是利用位运算和哈希表来找到最长超赞子字符串的长度。具体步骤如下：
     * <p>
     * 使用变量 state 来表示当前位置之前所有数字出现次数的奇偶性（偶数次为0，奇数次为1）。
     * 使用哈希表 seen 来记录每个 state 第一次出现的位置。
     * 遍历字符串 s，对于每个数字，更新 state，并检查当前 state 是否在哈希表中出现过。
     * 如果当前 state 已经在哈希表中出现过，计算当前位置与哈希表中记录的位置之间的距离，并更新最长超赞子字符串的长度。
     * 对于每个可能的奇数状态 odd_state（即将当前数字的奇偶性取反），检查是否在哈希表中出现过，同样更新最长超赞子字符串的长度。
     * 返回最长超赞子字符串的长度。
     * 这个算法利用位运算和哈希表快速计算出现次数的奇偶性，从而找到最长的符合条件的子字符串。
     *
     * @param s
     * @return
     */
    public static int longestAwesome(String s) {
        int max_length = 0;
        int state = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < s.length(); i++) {
            int digit = Character.getNumericValue(s.charAt(i));
            // 这行代码是将 state 中对应数字位置上的比特位取反。具体来说，1 << digit 是将 1 左移 digit 位，
            // 然后使用异或操作符 ^ 将 state 的对应位置上的比特位进行翻转。这样可以有效地表示每个数字出现的奇偶性状态。
            state ^= 1 << digit;
            if (seen.containsKey(state)) {
                max_length = Math.max(max_length, i - seen.get(state));
            } else {
                seen.put(state, i);
            }
            for (int j = 0; j < 10; j++) {
                int odd_state = state ^ (1 << j);
                if (seen.containsKey(odd_state)) {
                    max_length = Math.max(max_length, i - seen.get(odd_state));
                }
            }
        }
        return max_length;
    }

    public static int solve(String s) {
        // System.out.println("s: "+s);

        // 前缀和算法
        // 截至 s[i]（含），数字 [d] 的出现次数
        int[][] count = new int[s.length()][10];

        // count[0]
        for (int d = 0; d <= 9; d++) {
            count[0][d] = s.charAt(0) == '0' + d ? 1 : 0;
        }
        // System.out.println(Arrays.toString(count[0]));

        // count[1+]

        for (int i = 1; i < s.length(); i++) {
            for (int d = 0; d <= 9; d++) {
                count[i][d] = s.charAt(i) == '0' + d ? count[i - 1][d] + 1 : count[i - 1][d];
            }
            // System.out.println(Arrays.toString(count[i]));
        }

        // 计算
        // 字符串的子串 [i, j]（含），各个数字的数目。至多一个数字的数目为奇数，其他数字的数目为偶数
        int max = 1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                // 数字 [d] 的出现次数，为 count[j]-count[i-1]
                int[] times = new int[10];
                for (int d = 0; d <= 9; d++) {
                    if (i == 0) {
                        times[d] = count[j][d];
                    } else {
                        times[d] = count[j][d] - count[i - 1][d];
                    }
                }
                // 是否为回文串
                boolean isValid = true;
                int oddCount = 0; // 出现次数为奇数的数字个数
                for (int time : times) {
                    if (time % 2 == 1) {
                        oddCount++;
                        if (oddCount > 1) { // 为回文串，仅当至多一个数字的次数为奇数
                            isValid = false;
                            break;
                        }
                    }
                }
                // 统计回文串长度
                if (isValid) {
                    int length = j - i + 1;
                    max = Math.max(max, length);
                }
            }
        }
        return max;
    }


}
