package bishi.shunfeng;

/**
 * @author machenggong
 * @date 2021/3/15
 * @description
 */
public class Test {

    private Test() {

    }

    private static volatile Test instance = null;

    public static Test getInstance() {

        if (instance == null) {
            synchronized (Test.class) {
                if (instance == null) {
                    instance = new Test();
                }
            }
        }
        return instance;
    }

}
