package leetcode.offer;

import java.util.Arrays;

/**
 * @author machenggong
 * @date 2021/3/7
 * @description
 */
public class PrintNumbers {

    /**
     * 剑指 Offer 17. 打印从1到最大的n位数
     * @param n
     * @return
     */
    public static int[] printNumbers(int n) {
        int x = 1;
        for(int i = 0;i<n;i++){
            x = x*10;
        }
        int[] res = new int[x-1];
        for(int i = 0;i<x-1;i++){
            res[i] = i+1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers(3)));
    }

}
