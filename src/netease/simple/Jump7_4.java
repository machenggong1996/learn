package netease.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 跳跃
 *
 * @author machenggong
 * @since 2024/2/22
 */
public class Jump7_4 {

    /**
      7
      4 2 3 0 3 1 2
      5
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());

        String[] arrayStr = br.readLine().split(" ");
        int[] array = new int[length];
        for(int i=0; i<length; i++) {
            array[i] = Integer.parseInt(arrayStr[i]);
        }

        int startIndex = Integer.parseInt(br.readLine());

        if (canEscape(array, startIndex)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }


    }

    /**
     * 这段代码首先创建一个队列，将起始位置加入队列。然后在每一步中，
     * 从队列中取出一个位置，检查它是否为出口（值为0），如果是，则返回true；
     * 否则，将从这个位置可以到达的位置（根据nums中的值计算）加入队列。
     * 若最终队列为空仍未找到出口，则返回false。
     * @param nums
     * @param start
     * @return
     */
    public static boolean canEscape(int[] nums, int start) {
        int n = nums.length;
        // 没有这个记录会运行超时 内存超限
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int position = queue.poll();
            if (nums[position] == 0) {
                return true;
            }
            // 可以向前或者向后跳跃
            // 从当前位置可以跳到的位置
            // 不要写反了
            int[] positions = {position + nums[position], position - nums[position]};
            for (int step : positions) {
                // 记录跳过
                if (step >= 0 && step < n && !visited[step]) {
                    visited[step] = true;
                    queue.add(step);
                }
            }
        }
        return false;
    }

}
