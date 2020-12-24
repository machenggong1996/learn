package leetcode.array;

/**
 * @author machenggong
 * @date 2020/12/24
 * @description
 */
public class PassOutCandy {

    /**
     * 分发糖果
     *
     * @param ratings
     * @return
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        //当前递减序列的长度 dec
        //最近的递增序列的长度 inc
        //前一个同学分得的糖果数量 pre
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                //分数相同 糖果可以不同 下一个直接为1
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5, 3, 2, 1};
        int[] arr2 = new int[]{1, 3, 5, 2, 3, 3};
        System.out.println(candy(arr2));
    }

}
