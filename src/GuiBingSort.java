/**
 * Created by machenggong on 2019/5/7.
 */
public class GuiBingSort {

    public static void main(String[] args) {
        //定义一个无序数组
        //int[] arr = {3, 1, 2, 4, 5, 7, 6, 8};
        int[] arr = {3, 1, 2};
        //left在这里定义，控制左半部分的首元素
        int left = 0;
        //right指向最后一个元素
        int right = arr.length - 1;
        //调用排序函数
        sort(arr, left, right);
        //输出排序后的数组
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] sort(int[] arr, int left, int right) {
        //mid其实是作为分割的指针
        int mid = (left + right) / 2;
        //递归拆分，调用归并（这里是关键，需要读者好好思考）
        //读者可以用元素少一点的数组先思考明白了，再去想递归是什么回事
        //递归的作用实际上是在控制数组下标
        //递归会返回到上一层调用函数
        if (left < right) {
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merger(arr, left, right, mid);
        }
        return arr;
    }

    //归并函数
    public static void merger(int[] arr, int left, int right, int mid) {
        //定义一个新数组来存放排序后的数
        int[] temp = new int[right - left + 1];
        //x控制新数组的下表
        int x = 0;
        //j指向右半部分数组的首元素
        int j = mid + 1;
        //将left赋值给l，最后覆盖的时候要用到，否则left值被改变
        int l = left;
        while (left <= mid && j <= right) {
            if (arr[left] < arr[j]) {
                temp[x++] = arr[left++];
            } else {
                temp[x++] = arr[j++];
            }
        }
        //处理剩余数组
        while (left <= mid) {
            temp[x++] = arr[left++];
        }
        //处理剩余数组
        while (j <= right) {
            temp[x++] = arr[j++];
        }
        //覆盖原来的数组
        for (int i = 0; i < temp.length; i++) {
            arr[i + l] = temp[i];//
        }
    }

}
