package leetcode;

/**
 * Created by machenggong on 2020/3/10.
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] arr) {

        int i = 0;
        int j = arr.length - 1;
        int max = 0;

        while (i < j) {
            int w = j - i;
            int h = arr[i] > arr[j] ? arr[j] : arr[i];
            int m = h * w;
            max = max > m ? max : m;
            if (arr[i] > arr[j]) {
                j--;
            } else {
                i++;
            }

        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(arr));
    }

}
