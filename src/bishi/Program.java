package bishi;

/**
 * Created by machenggong on 2020/3/7.
 */
public class Program {

    public static void main(String[] args) {
        int[] p = new int[]{10, 7, 5, 4};

        for (int i = 0; i < 120; i++) {
            //获取最大值及索引
            int[] arrnew = getMaxValueAndIndex(p);
            p[arrnew[1]] = arrnew[0] - p.length + 1;
            for (int j = 0; j < p.length; j++) {
                if (j != arrnew[1]) {
                    p[j] = p[j] + 1;
                }
            }

        }
        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i]);
        }
    }

    public static int[] getMaxValueAndIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int maxIndex = 0;
        int[] arrNew = new int[2];
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[maxIndex] < arr[i + 1]) {
                maxIndex = i + 1;
            }
        }
        arrNew[0] = arr[maxIndex];
        arrNew[1] = maxIndex;
        return arrNew;
    }

}
