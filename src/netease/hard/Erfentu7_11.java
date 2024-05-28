package netease.hard;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 7-11 判断二分图
 *
 * @author machenggong
 * @since 2024/3/13
 */
public class Erfentu7_11 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        str = str.substring(1, str.length() - 1);
        String[] data = str.split(";");
        int[] visited = new int[data.length];
        Queue<Integer> queue = new ArrayDeque<>();
        visited[0] = 1;
        queue.offer(0);
        while (!queue.isEmpty()) {
            int father = queue.poll();
            int fatherGroup = visited[father];
            if ("[]".equals(data[father])) continue;
            String nextStr = data[father].replace("[", "").replace("]", "");
            for (String nodeStr : nextStr.split(",")) {
                int next = Integer.parseInt(nodeStr);
                if (visited[next] == visited[father]) {
                    System.out.println(false);
                    return;
                }
                if (visited[next] != 0) continue;
                visited[next] = fatherGroup == 1 ? 2 : 1;
                queue.offer(next);
            }
        }
        System.out.println(true);
    }

}
