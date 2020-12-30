package designpatterns.template.soyamilk;

/**
 * @author machenggong
 * @date 2020/12/30
 * @description
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("制作红豆豆浆");
        RedBeanSoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();
        System.out.println("制作花生豆浆");
        PeanutSoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();
        System.out.println("制作纯豆浆");
        PureSoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }

}
