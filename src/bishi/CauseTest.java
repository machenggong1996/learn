package bishi;

/**
 * @author machenggong
 * @date 2020/11/12
 */
public class CauseTest {

    public static void say() {
        int i = 0;
        try {
            System.out.println("START");
            div(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double div(int i) {
        double num = 0;
        try {
            System.out.println("start div");
            num = i / 0;
        } catch (Exception e) {
            //getCause() 会将异常抛出到调用方法
            e.getCause().getMessage();
        }
        return num;

    }

    public static void main(String[] args) {
        say();
    }

}
