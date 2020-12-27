package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description
 */
public class DVDPlayer {

    //创建单例模式
    private static final DVDPlayer instance = new DVDPlayer();

    private DVDPlayer(){}

    public static DVDPlayer getInstance(){
        return instance;
    }

    public void on(){
        System.out.println("DVD on");
    }

    public void off(){
        System.out.println("DVD off");
    }

    public void play(){
        System.out.println("DVD play");
    }

    public void pause(){
        System.out.println("DVD pause");
    }

}
