package leetcode;

/**
 * Created by machenggong on 2020/3/10.
 */
public class ContainerWithMostWater {

    /**
     * 11. 盛最多水的容器
     *
     * @param arr
     * @return
     */
    public static int maxArea(int[] arr) {

        int i = 0;
        int j = arr.length - 1;
        int max = 0;

        while (i < j) {
            int w = j - i;
            int h = Math.min(arr[i], arr[j]);
            int m = h * w;
            max = Math.max(max, m);
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
