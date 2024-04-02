package netease.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 7-3 拼接最大数 B站 https://www.bilibili.com/video/BV1Tg411x7va/?spm_id_from=333.337.search-card.all.click
 * https://leetcode.cn/problems/create-maximum-number/solutions/505931/pin-jie-zui-da-shu-by-leetcode-solution/
 *
 * @author machenggong
 * @since 2024/3/12
 */
public class MaxNumber7_3 {


    /*
 假设数组一为[3,4,6,5]、数组二为[9,1,2,5,8,3]、k = 5;
 组合情况有0 + 5、1 + 4、2 + 3、3 + 2、4 + 1五种情况,就是从此五种情况取出组合最大的一种;
 Math.max(0, k - n)表示若数组二的元素个数 >= k,则数组一的元素个数可以从0开始取,否则在数组二的大小基础上补.
 */
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[k];
        for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
            int[] sub1 = maxArr(nums1, i);
            int[] sub2 = maxArr(nums2, k - i);
            int[] arr = merge(sub1, sub2, k);
            if (gt(arr, 0, res, 0)) res = arr;
        }
        return res;
    }

    /*
    假设选择了2 + 3的情况,分别从两个数组取出相应元素个数的最大组合，对数组一来说就是[6,5],对数组二来说是[9,8,3];
    n - i : 当前数组中,当前下标到结尾还有多少个元素;
    j : 当前数组中i之前有多少个数加入到最大组合中;
    n - i + j > k <=> n - i - 1 + j >= k : 当前下标的元素大于最大组合的末尾元素，就需要弹出,弹出后的元素减少,故j--,
    n - i(数组剩余元素) - 1(去掉最大组合末尾元素) + j(最大组合中剩余元素)时刻保持 >= k;
    if j < k : 先将最大组合填满再进行比较替换操作
     */
    private static int[] maxArr(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        for (int i = 0, j = 0; i < n; i++) {
            while (n - i + j > k && j > 0 && nums[i] > res[j - 1]) {
                j--;
            }
            if (j < k) {
                res[j++] = nums[i];
            }
        }
        return res;
    }

    /*
    假设数组一最大组合为[6,5],数组二最大组合为[9,8,3],进行双指针排序,排序后为[9,8,6,5,3]
     */
    private static int[] merge(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        for (int i = 0, j = 0, r = 0; r < k; r++)
            res[r] = gt(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        return res;
    }

    /*
    比较两数组相应位置大小,相等就一直跳过,直到不相等就比较.
     */
    private static boolean gt(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }

    public static int[] maxNumber1(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[k];
        int[] mergeList = new int[nums1.length + nums2.length];
        int i = 0;
        for (int n1 : nums1) {
            mergeList[i] = n1;
            i++;
        }
        for (int n2 : nums2) {
            mergeList[i] = n2;
            i++;
        }
        Arrays.sort(mergeList);
        for (int j = 0; j < k; j++) {
            res[j] = mergeList[mergeList.length - 1 - j];
        }
        return res;
    }

    public static int[] maxNumber2(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        Stack<Integer> stack1 = new Stack<>();
        for (int i = nums1.length - 1; i >= 0; i--) {
            stack1.push(nums1[i]);
        }
        Stack<Integer> stack2 = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            stack2.push(nums2[i]);
        }
        List<Integer> list = new ArrayList<>();
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int num1 = stack1.peek();
            int num2 = stack2.peek();
            if (num1 >= num2) {
                list.add(stack1.pop());
            } else {
                list.add(stack2.pop());
            }
        }
        while (!stack1.isEmpty()) {
            list.add(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            list.add(stack2.pop());
        }
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取输入
        String[] nums1Str = scanner.nextLine().split(",");
        String[] nums2Str = scanner.nextLine().split(",");
        int k = Integer.parseInt(scanner.nextLine());

        int[] nums1 = new int[nums1Str.length];
        int[] nums2 = new int[nums2Str.length];

        for (int i = 0; i < nums1Str.length; i++) {
            nums1[i] = Integer.parseInt(nums1Str[i]);
        }

        for (int i = 0; i < nums2Str.length; i++) {
            nums2[i] = Integer.parseInt(nums2Str[i]);
        }

        // 计算结果
        int[] result = maxNumber(nums1, nums2, k);
        List<String> resultInt = Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.toList());
        System.out.println(String.join(",", resultInt));
    }

}
