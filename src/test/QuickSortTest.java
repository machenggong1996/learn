package test;

/**
 * Created by mache on 2019/10/25.
 */
public class QuickSortTest {

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        i = low;
        j = high;
        //基准
        if (low > high) {
            return;
        }
        temp = arr[low];
        while (i < j) {

            while (i < j && temp <= arr[j]) {
                j--;
            }

            while (i < j && temp >= arr[i]) {
                i++;
            }

            //
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }

        arr[low] = arr[i];
        arr[i] = temp;

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
