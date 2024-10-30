package suanfa2024;

/**
 * 26. 删除有序数组中的重复项
 *
 * @author machenggong
 * @since 2024/10/10
 */
public class RemoveDuplicates {

    // 原地删除 不使用额外空间 i j 双指针
    public static int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    public static int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < n; j++) {
            if(nums[i] != nums[j]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicates1(new int[]{0, 1, 2, 2, 3, 3, 4}));
    }

}
