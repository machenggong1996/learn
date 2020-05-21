package leetcode.array;

/**
 * @author machenggong
 * @date 2020/05/14
 */
public class SingleNumber {

    public static int singleNumber(int[] nums) {

        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res = res^nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] { 4,1,2,1,2 }));
    }

}
