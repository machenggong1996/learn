package designpatterns.singleton.type5;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public class SingletonTest05 {

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

    // 2.本类内部创建对象实例
    private static Singleton instance;


    public static Singleton getInstance() {
        synchronized (Singleton.class){
            if (instance == null) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}