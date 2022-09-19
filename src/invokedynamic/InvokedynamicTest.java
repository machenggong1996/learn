package invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author machenggong
 * @since 2022/9/14
 */
public class InvokedynamicTest {

    public static void main(String[] args) {
        Object rcvr = "a";
        try {
            MethodType mt = MethodType.methodType(int.class); // 方法签名
            MethodHandles.Lookup l = MethodHandles.lookup(); // 调用者，也就是当前类。调用者决定有没有权限能访问到方法
            MethodHandle mh = l.findVirtual(rcvr.getClass(), "hashCode", mt); //分别是定义方法的类，方法名，签名
            int ret;
            try {
                ret = (int)mh.invoke(rcvr); // 代码，第一个参数就是接收者
                // 97
                System.out.println(ret);
                // 97
                System.out.println(rcvr.hashCode());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } catch (IllegalArgumentException | NoSuchMethodException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
