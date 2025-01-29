package leetcode.array;

import java.util.Arrays;

/**
 * Created by machenggong on 2020/3/20.
 */
public class PlusOne {

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        //跳出for循环，说明数字全部是9
        int[] temp = new int[digits.length + 1];
        temp[0] = 1;
        return temp;
    }

    public static int[] plusOne1(int[] digits) {
        for(int i = digits.length - 1; i >=0;i--){
            digits[i]++;
            digits[i] = digits[i]%10;
            if(digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9, 9})));
    }

}
