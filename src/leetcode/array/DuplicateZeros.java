package leetcode.array;

/**
 * @author machenggong
 * @since 2022/6/17
 */
public class DuplicateZeros {

    /**
     * 1089. 复写零
     * @param arr
     */
    public static void duplicateZeros(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                //此时手动把后边的所有元素都后移一位：
                for (int j = arr.length - 1; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        duplicateZeros(arr);
    }

}
