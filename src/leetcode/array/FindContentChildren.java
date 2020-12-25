package leetcode.array;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class FindContentChildren {

    /**
     * 分发饼干
     * 尽可能满足孩子胃口
     *
     * @param g 孩子胃口
     * @param s 饼干尺寸
     * @return 满足个数
     */
    public static int findContentChildren(int[] g, int[] s) {
        int child = 0;
        int cookie = 0;
        Arrays.sort(g);  //先将饼干 和 孩子所需大小都进行排序
        Arrays.sort(s);
        while (child < g.length && cookie < s.length ){ //当其中一个遍历就结束
            if (g[child] <= s[cookie]){ //当用当前饼干可以满足当前孩子的需求，可以满足的孩子数量+1
                child++;
            }
            cookie++; // 饼干只可以用一次，因为饼干如果小的话，就是无法满足被抛弃，满足的话就是被用了
        }
        return child;
    }

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2}, new int[]{1, 2,3}));
    }

}
