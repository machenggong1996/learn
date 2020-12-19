package designpatterns.singleton.type6;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description 静态内部类
 */
public class SingletonTest06 {

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

    // 静态内部类 外部类被装载的时候 内部类不会被装载
    private static class SingletonInstance {
        private final static Singleton INSTANCE = new Singleton();
    }


    public static Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}