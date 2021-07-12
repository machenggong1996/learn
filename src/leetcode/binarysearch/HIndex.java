package leetcode.binarysearch;

import java.util.Arrays;

/**
 * @author machenggong
 * @since 2021/7/12
 */
public class HIndex {

    /**
     * 275. H 指数 II
     *
     * @param citations
     * @return
     */
    public static int hIndexII(int[] citations) {
        int n = citations.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }

    /**
     * 274. H 指数 I
     *
     * @param citations
     * @return
     */
    public static int hIndexI(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        // citations[i] 引用次数 h论文数量
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

    public static void main(String[] args) {
        int[] citations1 = {3, 0, 6, 1, 5};
        int[] citations2 = {0, 1, 3, 5, 6};
        System.out.println(hIndexI(citations1));
        System.out.println(hIndexII(citations2));
    }


}
