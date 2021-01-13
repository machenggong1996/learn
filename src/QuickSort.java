import java.util.Arrays;

/**
 * Created by machenggong on 2019/5/22.
 */
public class QuickSort {
    /**
     * 快速排序使一遍大于基准一边小于基准
     * <p>
     * <a href="https://blog.csdn.net/luzhensmart/article/details/112505063"a/>关于快速排序排序方向问题</a>
     * <p>
     * <p>
     * 最后i、j 停留的位置的值肯定是要 小于 key 的 此时交换索引 j 和最左边元素key 符合将小于key的值放到key左边这一条件
     *
     * @param arr
     * @param low
     * @param high
     */

    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            //一定要先看右边 j先减 所以j要在前面
            //先看右边，依次往左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换，使基准左侧比基准小基准右侧比基准大
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, i - 1);
        //递归调用右半数组
        quickSort(arr, i + 1, high);
    }

    /**
     * 快速排序从小到大
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort1(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];

        while (i < j) {

            //一定要先看右边 j先减 所以j要在前面
            //先看右边，依次往左递减
            while (arr[j] <= temp && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (arr[i] >= temp && i < j) {
                i++;
            }

            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换，使基准左侧比基准小基准右侧比基准大
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort1(arr, low, i - 1);
        //递归调用右半数组
        quickSort1(arr, i + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 2, 4, 7, 62, 3, 4, 2, 1, 8, 9, 19};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


}
