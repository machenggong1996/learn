package designpatterns.singleton.type3;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description 懒汉式
 */
public class SingletonTest03 {

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
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}