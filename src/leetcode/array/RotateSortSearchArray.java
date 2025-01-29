package leetcode.array;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * 搜索旋转排序数组
 * @author machenggong
 * @date 2020/04/27
 */
public class RotateSortSearchArray {

    /**
     * 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
                //说明右侧有序
            } else if (nums[mid] < nums[right]) {
                //target在二者之间
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                //说明左侧有序
                //target在二者之间
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] { 4, 5, 6, 7, 0, 1, 2 }, 0));
    }

}
