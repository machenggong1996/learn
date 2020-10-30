package leetcode.tanxin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author machenggong
 * @date 2020/10/22
 */
public class PartitionLabels {
    /**
     * 763. 划分字母区间
     * 贪心算法
     * @param S
     * @return
     */

    public static List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        int length = S.length();
        for (int i = 0; i < length; i++) {
            last[S.charAt(i) - 'a'] = i;
        }
        List<Integer> partition = new ArrayList<Integer>();
        int start = 0, end = 0;
        for (int i = 0; i < length; i++) {
            end = Math.max(end, last[S.charAt(i) - 'a']);
            if (i == end) {
                partition.add(end - start + 1);
                start = end + 1;
            }
        }
        return partition;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegehijhklij"));
    }

}
