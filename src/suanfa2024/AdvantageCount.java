package suanfa2024;

import java.util.Arrays;

/**
 * leetcode 870. 优势洗牌
 * https://leetcode.cn/problems/advantage-shuffle/solutions/1/you-shi-xi-pai-by-leetcode-solution-sqsf/
 *
 * @author machenggong
 * @since 2024/10/9
 */
public class AdvantageCount {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx1[i] = i;
            idx2[i] = i;
        }
        Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);

        int[] ans = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; ++i) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                ans[idx2[left]] = nums1[idx1[i]];
                ++left;
            } else {
                // nums1数组元素都小于nums2的元素
                ans[idx2[right]] = nums1[idx1[i]];
                --right;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        int[] nums2 = {3, 10, 4, 11};

        AdvantageCount advantageCount = new AdvantageCount();
        int[] ans = advantageCount.advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(ans));

    }


}
