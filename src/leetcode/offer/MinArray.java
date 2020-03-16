package leetcode.offer;

/**
 * Created by machenggong on 2020/3/16.
 */
public class MinArray {

    public static int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while (l < r) {
            int mid = ((r - l) >> 1) + l;
            //只要右边比中间大，那右边一定是有序数组
            if (numbers[r] > numbers[mid]) {
                r = mid;
            } else if (numbers[r] < numbers[mid]) {
                l = mid + 1;
                //去重
            } else {
                r--;
            }
        }
        return numbers[l];
    }

    public static void main(String[] args) {
        System.out.println(minArray(new int[]{2,2,2,3,0,1}));
    }
}
