/**
 * Created by machenggong on 2019/5/10.
 */
public class DPMaxSequence {
    /*
     *  Fk=max{Fk-1+Ak,Ak}
     *  Fk是前k项的和，Ak是第k项的值
     */

    public static int maxsequence(int a[], int len) {
        int maxsum, maxhere;
        maxsum = maxhere = a[0];   //初始化最大和为a【0】
        for (int i = 1; i < len; i++) {
            if (maxhere <= 0)
                maxhere = a[i];  //如果前面位置最大连续子序列和小于等于0，则以当前位置i结尾的最大连续子序列和为a[i]
            else
                maxhere += a[i]; //如果前面位置最大连续子序列和大于0，则以当前位置i结尾的最大连续子序列和为它们两者之和
            if (maxhere > maxsum) {
                maxsum = maxhere;  //更新最大连续子序列和
            }
        }
        return maxsum;
    }

    public static int MaxContinueArraySum(int a[]) {
        int n = a.length;
        int max = a[0];
        int sum = a[0];
        for (int i = 1; i < n; i++) {
            sum = Math.max(sum + a[i], a[i]);
            if (sum >= max) {
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 0, -3, 4, 1};
        int m = maxsequence(a, a.length);
        System.out.println(m);
    }

}
