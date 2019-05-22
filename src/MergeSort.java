/**
 * Created by machenggong on 2019/5/7.
 */
public class MergeSort {

    public static void main(String[] args) {
        //定义两个整型数组
        int[] arr1 = {1, 3, 5, 8, 12, 45, 89};
        int[] arr2 = {2, 4, 6, 7, 9, 10, 11};
        //调用归并排序函数，赋值给新数组temp
        int[] temp = mergerSort(arr1, arr2);
        //输出排序后的数组
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "  ");
        }
    }

    //定义归并排序算法
    public static int[] mergerSort(int[] a1, int[] a2) {
        //left控制a1数组的下标
        int left = 0;
        //right控制a2数组的下标
        int right = 0;
        //x控制新数组的下标
        int x = 0;
        //定义一个新数组arr
        int[] arr = new int[a1.length + a2.length];
        while (left < a1.length && right < a2.length) {
            //如果left指向的数小于right指向的数
            //就把arr[left]写入到新数组arr中
            if (a1[left] < a2[right]) {
                arr[x++] = a1[left++];
            }
            //如果right指向的数小于left指向的数
            //就把arr[right]写入到新数组arr中
            else {
                arr[x++] = a2[right++];
            }
        }
        //比较完后，若a1数组有剩余，将剩余部分写入新数组的后面
        while (left < a1.length) {
            arr[x++] = a1[left++];
        }
        //比较完后，若a2数组有剩余，将剩余部分写入新数组的后面
        while (right < a2.length) {
            arr[x++] = a2[right++];
        }
        //返回数组
        return arr;
    }
}
