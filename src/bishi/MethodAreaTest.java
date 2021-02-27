package bishi;

/**
 * @author machenggong
 * @date 2021/2/25
 * @description
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
        System.out.println(2<<3);
    }

}

class Order{

    public static int count = 1;

    public static void hello(){
        System.out.println("hello");
    }

}
