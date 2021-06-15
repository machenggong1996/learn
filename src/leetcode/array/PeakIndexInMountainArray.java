package leetcode.array;

/**
 * @author machenggong
 * @since 2021/6/15
 */
public class PeakIndexInMountainArray {

    /**
     * 852. 山脉数组的峰顶索引 二分查找
     *
     * @param arr
     * @return
     */
    public static int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int left = 1, right = n - 2, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[mid + 1]) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0, 10, 5, 2};
        System.out.println(peakIndexInMountainArray(arr));
    }

}
