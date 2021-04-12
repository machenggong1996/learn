package bishi;

/**
 * @author machenggong
 * @since 2021/4/8
 */
public class Switch {

    public static void main(String[] args) {
        System.out.println(test(2));
    }

    public static int test(int i){
        switch (i){
            case 1:
                System.out.println("------:"+i);
            case 2:
                System.out.println("------:"+i);
            case 3:
                System.out.println("------:"+3);
                return i;
        }
        return -1;
    }

}
