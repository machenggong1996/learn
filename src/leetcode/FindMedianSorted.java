package leetcode;

/**
 * Created by machenggong on 2019/6/24.
 */
public class FindMedianSorted {

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // 确保数组A长度小于数组B长度，因此确保 j 不会为负数
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        /**
         *  A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
         *  B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
         */
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            // 初始从数组A的中间分隔
            int i = (iMin + iMax) / 2;
            // 确保条件1：`i + j == m + n - i - j`
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        int nums1[] = {1, 3};
        int nums2[] = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
