package leetcode;

import java.util.Scanner;

/**
 * Created by mache on 2019/10/25.
 */
public class TwoDArray {

    //从右上角开始查找target元素
    public boolean find(int[][] array, int target) {
        //首先判断数组不为空，否则直接返回false
        if (array != null && array.length > 0 && array[0].length > 0) {
            int row = 0; //初始化行的值
            int col = array[0].length - 1; //初始化列的值
            //循环遍历判断，寻找target
            while (row <= array.length - 1 && col >= 0) {
                if (target == array[row][col]) {
                    return true;
                } else if (target < array[row][col]) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return false;
    }

    //从左下角开始查找target元素
    public boolean find1(int[][] array, int target) {
        //首先判断数组不为空，否则直接返回false
        if (array != null && array.length > 0 && array[0].length > 0) {
            int row = array.length - 1; //初始化行的值
            int col = 0; //初始化列的值
            //循环遍历判断，寻找target
            while (row >= 0 && col <= array[0].length - 1) {
                if (target == array[row][col]) {
                    return true;
                } else if (target < array[row][col]) {
                    row--;
                } else {
                    col++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int array[][] = new int[3][4];
//        Scanner sc = new Scanner(System.in);
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 4; ++j) {
//                array[i][j] = sc.nextInt();
//            }
//        }
        int[][] arr = {{1,2,3},{4,5,6}};
        //int target = sc.nextInt();
        TwoDArray m1 = new TwoDArray();
        System.out.println(m1.find1(arr, 6));
    }

}
