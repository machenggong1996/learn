package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 立体声
 */
public class Stereo {

    //创建单例模式
    private static final Stereo instance = new Stereo();

    private Stereo() {
    }

    public static Stereo getInstance() {
        return instance;
    }

    public void on(){
        System.out.println("Stereo on");
    }

    public void off(){
        System.out.println("Stereo off");
    }

    public void up(){
        System.out.println("Stereo up");
    }

    public void down(){
        System.out.println("Stereo down");
    }

}
