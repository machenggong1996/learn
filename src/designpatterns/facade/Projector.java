package designpatterns.facade;

/**
 * @author machenggong
 * @date 2020/12/26
 * @description 投影仪
 */
public class Projector {

    //创建单例模式
    private static final Projector instance = new Projector();

    private Projector() {
    }

    public static Projector getInstance() {
        return instance;
    }

    public void on(){
        System.out.println("Projector on");
    }

    public void off(){
        System.out.println("Projector off");
    }

    public void focus(){
        System.out.println("Projector focus");
    }


}
