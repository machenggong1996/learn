package designpatterns.singleton.type8;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description 双重校验锁
 */
public class SingletonTest08 {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);
        System.out.println(singleton1.hashCode());
        System.out.println(singleton2.hashCode());
    }
}

class Singleton {

    // 1. 构造器私有化
    private Singleton() {

    }

    /**
     * volatile 防止指令重排
     */
    private static volatile Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}