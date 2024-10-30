package suanfa2024;

/**
 * 斐波那契
 *
 * @author machenggong
 * @since 2024/10/11
 */
public class Fib {

    // 斐波那契数列递归 有很多重复计算
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 记忆递归

    public static int fibonacci1(int n) {
        int[] arr = new int[n+1];
        return recurse(arr, n);
    }

    public static int recurse(int[] arr , int num){
        if (num == 0) {
            return 0;
        }
        if (num == 1){
            return 1;
        }
        if(arr[num] != 0){
            return arr[num];
        }
        arr[num] = recurse(arr, num -1) + recurse(arr, num - 2);
        return arr[num];
    }

    // 双指针
    public static int fibonacci2(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1){
            return 1;
        }
        int low = 0,high = 1;
        for (int i = 2; i <= num; i++){
            int sum = low + high;
            low = high;
            high = sum;
        }
        return high;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(fibonacci1(10));
        System.out.println(fibonacci2(10));
    }

}
