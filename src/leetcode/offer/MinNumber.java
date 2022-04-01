package leetcode.offer;

/**
 * @author machenggong
 * @date 2020/07/09
 */
public class MinNumber {

    /**
     * 对字符串进行排序 剑指 Offer 45. 把数组排成最小的数
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        fastSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        return res.toString();
    }

    void fastSort(String[] strs, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l, j = r;
        String tmp = strs[i];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) {
                j--;
            }
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) {
                i++;
            }
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        fastSort(strs, l, i - 1);
        fastSort(strs, i + 1, r);
    }

    public static void main(String[] args) {
        MinNumber minNumber = new MinNumber();
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(minNumber.minNumber(nums));
    }

}
