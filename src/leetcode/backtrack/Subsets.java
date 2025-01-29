package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * 78 子集
 *
 * @author machenggong
 * @since 2024/12/16
 */
public class Subsets {

    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }

    List<Integer> t1 = new ArrayList<Integer>();
    List<List<Integer>> ans1 = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets1(int[] nums) {
        dfs(0, nums);
        return ans1;
    }

    /**
     * 深度优先遍历
     *
     * @param cur  当前位置
     * @param nums 数组
     */
    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            ans1.add(new ArrayList<Integer>(t1));
            return;
        }
        t1.add(nums[cur]);
        dfs(cur + 1, nums);
        t1.remove(t1.size() - 1);
        dfs(cur + 1, nums);
    }

    List<List<Integer>> list = new ArrayList<List<Integer>>();
    List<Integer> children = new ArrayList<Integer>();

    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        list.add(children);
        backtracking(0, nums);
        return list;
    }

    public void backtracking(int start, int[] nums) {
        for (int i = start; i < nums.length; i++) {
            children.add(nums[i]);
            list.add(new ArrayList<Integer>(children));

            backtracking(i + 1, nums);

            children.remove(children.size() - 1);
        }
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        System.out.println(subsets.subsets2(new int[]{1, 2, 3}));
    }

}
