package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 影院灯光
 */
public class TheaterLight {

    //创建单例模式
    private static final TheaterLight instance = new TheaterLight();

    private TheaterLight() {
    }

    public static TheaterLight getInstance() {
        return instance;
    }

    public void on(){
        System.out.println("TheaterLight on");
    }

    public void off(){
        System.out.println("TheaterLight off");
    }

    public void dim(){
        System.out.println("TheaterLight dim");
    }

    public void bright(){
        System.out.println("TheaterLight bright");
    }

}
