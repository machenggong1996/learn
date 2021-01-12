import java.util.Arrays;

/**
 * Created by mache on 2019/10/23.
 */
public class MaopaoYouhua {

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 7, 9, 8};
        sort(arr);

        for (int t : arr) {
            System.out.print(t + " ");
        }

    }

    /**
     * 前半部有序
     * @param arr
     */
    public static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { //控制进行比较的轮数
            boolean sorted = true;//考虑有序，减少比较轮数
            System.out.println("第" + (i + 1) + "烫");
            for (int j = 0; j < arr.length - 1 - i; j++) {//每轮进行比较的次数
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    sorted = false;  //考虑有序，减少趟数
                }
                System.out.println(Arrays.toString(arr));
            }
            if (sorted) {
                break;
            }
        }
    }

    /**
     * 进一步优化 后半部有序
     * @param array
     */
    private static void sort1(int array[]) {

        int tmp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            System.out.println("第" + (i + 1) + "烫");
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
                System.out.println(Arrays.toString(array));
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }

}
