package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 屏幕
 */
public class Screen {

    //创建单例模式
    private static final Screen instance = new Screen();

    private Screen() {
    }

    public static Screen getInstance() {
        return instance;
    }

    public void up(){
        System.out.println("Screen up");
    }

    public void down(){
        System.out.println("Screen down");
    }

}
