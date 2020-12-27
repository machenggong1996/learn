package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description
 */
public class PopCorn {

    //创建单例模式
    private static final PopCorn instance = new PopCorn();

    private PopCorn() {
    }

    public static PopCorn getInstance() {
        return instance;
    }

    public void on(){
        System.out.println("Pop corn on");
    }

    public void off(){
        System.out.println("Pop corn off");
    }

    public void pop(){
        System.out.println("Pop corn pop");
    }

}
