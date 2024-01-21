import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author machenggong
 * @since 2024/1/19
 */
public class FullHalfConversion {

    public static void main(String[] args) {
        Executors.newCachedThreadPool();
        System.out.println(func());
        List list = new ArrayList<String>();
        list.add("1");
        list.add(2);

    }

    public static int func(){
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println(1);
            throw new RuntimeException();
        } finally {
            System.out.println(2);
            return 2;
        }
    }

}
