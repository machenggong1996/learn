package netease.hard;

import java.util.Scanner;
import java.util.Stack;

/**
 * 7-13 最多能完成排序的块
 *
 * @author machenggong
 * @since 2024/3/12
 */
public class MaxChunksToSorted7_13 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] parts = sc.nextLine().split(" ");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i]);
        }
        // 分隔后的排序和不分隔的排序结果相同
        int result = maxChunksToSorted1(arr);
        System.out.println(result);
    }

    /**
     * 排序 + 哈希表
     * 原数组是正序的时候，分隔的块数最多
     * 3 2 4 1 5 拆分为[3 2 4 1][5]
     *
     * @param arr
     * @return
     */
    public static int maxChunksToSorted1(int[] arr) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int num : arr) {
            if (stack.isEmpty() || num >= stack.peek()) {
                stack.push(num);
            } else {
                int mx = stack.pop();
                while (!stack.isEmpty() && stack.peek() > num) {
                    // 从后向前“穿越”一部分块，直至遇到一个块比这个元素小（或等于），被穿越的块合并为一个
                    stack.pop();
                }
                stack.push(mx);
            }
        }
        return stack.size();
    }

    public static int maxChunksToSorted(int[] arr) {
        // System.out.println(Arrays.toString(arr));

        // 从左到右存储现有的块，每个块只存储最大元素
        // 面对一个新元素：
        // - 1.如果元素大于等于最大的块，则形成一个新的块
        // - 2.如果元素小于最大的块，则会从后向前“穿越”一部分块，直至遇到一个块比这个元素小（或等于），被穿越的块合并为一个
        int[] list = new int[arr.length];
        list[0] = arr[0];
        int last = 0; // list 中最后一个元素的下标
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= list[last]) {
                // 1.新的块
                list[last + 1] = arr[i];
                last++;
            } else {
                // 2.穿越一部分快，导致这些块合并
                int index = last; // 穿越至这个块；更早的块比 arr[i] 小（或等于），以阻止穿越
                while (true) {
                    if (index == 0) { // 没有更多块了
                        break;
                    }
                    if (list[index - 1] <= arr[i]) { // 更早的块比 arr[i] 小（或等于），以阻止穿越
                        break;
                    }
                    index--;
                }
                // 更新，合并被穿越的块
                list[index] = list[last];
                last = index;
            }
        }
        return last + 1;
    }
}
