/**
 * @author machenggong
 * @since 2024/8/8
 */
public class Ntest {

    public static void main(String[] args) {
        // 输入
        int num = 18;
        int n = 2;
        for (int x = 1; x <= num; x++){
            for (int i = n; i< num ; i++){
                // i 拆成几个数 ， x 第一个数是多少
               int temp = i * x + ((i - 1) * i)/2;
               if(temp == num){
                   print(i, x);
                   break;
               }else if (temp > num)
                   break;
            }


        }


    }

    public static void print(int n , int x){
        for (int i = 0 ; i < n; i++){
            System.out.print((x + i) + ",");
        }
        System.out.println();
    }

}
