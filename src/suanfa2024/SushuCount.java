package suanfa2024;

/**
 * 统计素数个数
 *
 * @author machenggong
 * @since 2024/10/9
 */
public class SushuCount {

    // 暴力算法
    public static int bf(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 埃氏筛选
    // 素数 非素数（合数） 2是素数 3是素数 2*3是合数
    public static int eratosthenes(int n) {
        boolean[] isPrime = new boolean[n+1];// false代表素数
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                count++;
                // 2 * i 优化到 i * i 省去乘法运算
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(eratosthenes(100));
    }

}
