/**
 * Created by mache on 2019/10/23.
 */
public class MaopaoYouhua {

    public static void main(String[] args) {
        int[] arr = {3, 1, 5, 2, 9, 8};
        sort(arr);

        for (int t : arr) {
            System.out.print(t + " ");
        }
    }

    public static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) { //控制进行比较的轮数
            boolean sorted = true;//考虑有序，减少趟数
            System.out.println("第"+(i+1)+"烫");
            for (int j = 0; j < arr.length - 1 - i; j++) {//每轮进行比较的次数
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    sorted = false;  //考虑有序，减少趟数
                }
                //System.out.println(Arrays.toString(arr));
            }
            if (sorted) {
                break;
            }
        }
    }
}
