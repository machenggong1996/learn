package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by machenggong on 2020/2/14.
 */
public class SlideWindow {

    /**
     * 滑动窗口 https://blog.csdn.net/sinat_28108651/article/details/51780750
     * @param arr
     * @param n
     * @param w
     * @return
     */

    public static int[] slide(int[] arr, int n, int w) {
        if (arr == null || arr.length == 0 || arr.length < w) {
            return null;
        }

        LinkedList<Integer> queue = new LinkedList<Integer>();
        int[] result = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            //判断队首元素是否起作用
            if (queue.peekFirst() == i - w) {
                queue.pollFirst();
            }
            //向result中加入元素
            if (i >= w - 1) {
                result[index++] = arr[queue.peekFirst()];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(slide(arr, arr.length, 3)));
    }

}
