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
    public static int[] printNumbers1(int n) {
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

    int[] res;
    int count = 0;

    public int[] printNumbers(int n) {
        res = new int[(int)Math.pow(10, n) - 1];
        for(int digit = 1; digit < n + 1; digit++){
            for(char first = '1'; first <= '9'; first++){
                char[] num = new char[digit];
                num[0] = first;
                dfs(1, num, digit);
            }
        }
        return res;
    }

    private void dfs(int index, char[] num, int digit){
        if(index == digit){
            res[count++] = Integer.parseInt(String.valueOf(num));
            return;
        }
        for(char i = '0'; i <= '9'; i++){
            num[index] = i;
            dfs(index + 1, num, digit);
        }
    }

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        System.out.println(Arrays.toString(printNumbers.printNumbers(3)));
    }

}
