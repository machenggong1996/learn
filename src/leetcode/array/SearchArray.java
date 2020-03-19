package leetcode.array;

/**
 * Created by machenggong on 2020/3/18.
 */
public class SearchArray {

    public static int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] < nums[mid]) { //左边有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int searchTest(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                low++;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{6, 7, 0, 1, 2, 4, 5}, 0));
    }

}
