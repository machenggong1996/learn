package suanfa2024;

import java.util.LinkedList;
import java.util.Queue;

/**
 * dota2 参议院
 * https://leetcode.cn/problems/dota2-senate/description/
 *
 * @author machenggong
 * @since 2024/10/25
 */
public class Dota2Senate {

    // 贪心算法
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }

    public static void main(String[] args) {
        System.out.println(new Dota2Senate().predictPartyVictory("RDDRDR"));
    }


}
